package diagne.election_management_ws.Entities.Town;

import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import diagne.election_management_ws.Entities.VoteArea.VoteArea;
import diagne.election_management_ws.Model.Classes.Circonscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor @NoArgsConstructor
@Document
public class Town extends Circonscription
{
    @Id
    private String id;
    private Set<VoteArea> voteAreas = new HashSet<>();

}
