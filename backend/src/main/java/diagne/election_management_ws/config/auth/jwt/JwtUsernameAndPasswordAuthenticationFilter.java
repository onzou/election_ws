package diagne.election_management_ws.config.auth.jwt;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import diagne.election_management_ws.Entities.Elector.Elector;
import diagne.election_management_ws.Entities.Elector.ElectorService;
import diagne.election_management_ws.Entities.Token.Token;
import diagne.election_management_ws.Entities.Token.TokenService;
import diagne.election_management_ws.Model.Exceptions.BadAuthenticationException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    @Value("${application.jwt.secret}")
    private String jwtSecret;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final ElectorService electorService;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig,
                                                      ElectorService electorService, TokenService tokenService)
    {

        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.electorService = electorService;
        this.tokenService = tokenService;
        setFilterProcessesUrl("/elector/login");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException
    {
        throw new BadAuthenticationException("Adresse email ou mot de passe incorrect");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            InputStream inputStream = request.getInputStream();
            UsernameAndPasswordAuthenticationRequest authRequest = objectMapper.readValue(inputStream,
                                                                        UsernameAndPasswordAuthenticationRequest.class);
            Authentication auth = new UsernamePasswordAuthenticationToken(authRequest.getElectorNumber(),authRequest.getPassword());
            return authenticationManager.authenticate(auth);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException
    {
        String userName =  ((User) authResult.getPrincipal()).getUsername();
        Elector electorRetrieved = this.electorService.getElectorByNumber(userName);
        String token = Jwts.builder()
                .setSubject(electorRetrieved.getId().toString()) // getting id of the username ( 1 for example)
                .claim("authorities",authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getExpirationDuration())))
                .compact();

        tokenService.save(new Token(null,token,userName));

        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Expose-Headers", "UserId");
        response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header, UserId");
        response.addHeader("Authorization","Bearer "+token);
        response.addHeader("UserId",electorRetrieved.getId().toString());
    }

}
