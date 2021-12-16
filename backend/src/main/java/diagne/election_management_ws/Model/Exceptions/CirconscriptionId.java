package diagne.election_management_ws.Model.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@AllArgsConstructor @NoArgsConstructor
public class CirconscriptionId implements java.io.Serializable
{
    private String name;
    private String type;
}
