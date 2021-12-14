package diagne.election_management_ws.Model.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BadAuthenticationException extends RuntimeException
{
    private String message;
}
