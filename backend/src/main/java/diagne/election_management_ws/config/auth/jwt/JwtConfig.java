package diagne.election_management_ws.config.auth.jwt;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix = "application.jwt")
@Getter @Setter @NoArgsConstructor
public class JwtConfig
{
    private String tokenPrefix;
    private String header;
    private int expirationDuration;
}
