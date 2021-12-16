package diagne.election_management_ws.Entities.Circonscription;

import diagne.election_management_ws.Model.Exceptions.CirconscriptionId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "circonscription")
public class Circonscription
{

    @EmbeddedId
    private CirconscriptionId circonscriptionId;

    @Column(name = "longitude")
    private float longitude;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "number_of_subscribers")
    private int numberOfSubscribers;

    @OneToMany
    private Set<Circonscription> circonscriptions = new HashSet<>();
}
