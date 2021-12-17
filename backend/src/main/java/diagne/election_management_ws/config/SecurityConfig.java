package diagne.election_management_ws.config;

import com.google.common.base.Strings;
import diagne.election_management_ws.Entities.Elector.ElectorService;
import diagne.election_management_ws.Entities.Token.TokenService;
import diagne.election_management_ws.config.auth.jwt.JwtConfig;
import diagne.election_management_ws.config.auth.jwt.JwtTokenVerifier;
import diagne.election_management_ws.config.auth.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableWebSecurity
@CrossOrigin
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private final JwtConfig jwtConfig;
    private final ElectorService electorService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(JwtConfig jwtConfig,
                          ElectorService electorService,
                          TokenService tokenService,
                          PasswordEncoder passwordEncoder)
    {
        this.jwtConfig = jwtConfig;
        this.electorService = electorService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //electors
                .and()
                .authorizeRequests()
                .antMatchers("/elector/")
                .permitAll()

                //vote
                .and()
                .authorizeRequests()
                .antMatchers("/vote", HttpMethod.GET.name(),HttpMethod.POST.name())
                .hasAnyRole("ELECTOR","CANDIDATE")

                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, electorService, tokenService))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, tokenService),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(this.daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(this.passwordEncoder);
        provider.setUserDetailsService(this.electorService);
        return provider;
    }
}
