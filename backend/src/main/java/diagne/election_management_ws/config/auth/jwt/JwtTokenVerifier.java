package diagne.election_management_ws.config.auth.jwt;

import com.google.common.base.Strings;
import diagne.election_management_ws.Entities.Token.Token;
import diagne.election_management_ws.Entities.Token.TokenService;
import diagne.election_management_ws.Model.Exceptions.BadAuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter
{

    private final TokenService tokenService;
    private final JwtConfig jwtConfig;


    @Autowired
    public JwtTokenVerifier(JwtConfig jwtConfig,
                            TokenService tokenService)
    {
        this.jwtConfig = jwtConfig;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException
    {

        String authorizationHeader = httpServletRequest.getHeader(jwtConfig.getHeader());
        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix()))
        {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(),"");
        if(isTokenRevoked(token))
        {
            throw new BadAuthenticationException("invalid token!");
        }
        try
        {
            //use parseClaimsJwt if there is no key signing and user parseClaimsJws otherwise.
            Jwt<Header, Claims> claimsJws = Jwts.parser()
                                        .parseClaimsJwt(token);
            Claims tokenBody = claimsJws.getBody();
            String username = tokenBody.getSubject();

            List<Map<String, String>> authorities = (List<Map<String, String>>) tokenBody.get("authorities");

            Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                                            .map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
                                            .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    grantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(JwtException e)
        {
            throw new BadAuthenticationException("invalid token!");
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private boolean isTokenRevoked(String token)
    {
        List<Token> tokenToCheck = tokenService.findTokenByValue(token);
        return tokenToCheck.isEmpty();
    }
}
