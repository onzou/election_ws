package diagne.election_management_ws.Entities.VoteOffice;

import diagne.election_management_ws.Entities.Vote.Vote;
import diagne.election_management_ws.Entities.VotersList.VotersList;
import diagne.election_management_ws.Model.Area;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class VoteOffice
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private VotersList votersList;

    @OneToMany
    private Set<Vote> votes = new HashSet<>();
}
