package diagne.election_management_ws.Model.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor @NoArgsConstructor
public class EntityNotFoundException extends RuntimeException
{
    private String message;
}
