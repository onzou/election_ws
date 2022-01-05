package diagne.election_management_ws.Entities.Elector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor @NoArgsConstructor
public class ElectorException extends RuntimeException
{
    private String message;
}
