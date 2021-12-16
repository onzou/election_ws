package diagne.election_management_ws.Model.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor @NoArgsConstructor
public class CirconscriptionId implements java.io.Serializable
{
    @Column(name = "circonscription_name")
    private String name;

    @Column(name = "circonscription_type")
    private String type;
}
