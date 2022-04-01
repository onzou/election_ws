package diagne.election_management_ws.Model.Exceptions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class BadAuthenticationException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    private String message;
}
