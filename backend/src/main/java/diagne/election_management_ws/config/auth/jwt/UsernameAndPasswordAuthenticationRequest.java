package diagne.election_management_ws.config.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class UsernameAndPasswordAuthenticationRequest
{
    private String electorNumber;
    private String password;
}
