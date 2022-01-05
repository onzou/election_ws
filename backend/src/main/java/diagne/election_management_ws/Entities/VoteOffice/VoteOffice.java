package diagne.election_management_ws.Entities.VoteOffice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import diagne.election_management_ws.Entities.Vote.Vote;
import diagne.election_management_ws.Entities.VotersList.VotersList;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne()
    private VotersList votersList;

    @ManyToOne
    private Arrondissement arrondissement;

    @OneToMany
    private Set<Vote> votes = new HashSet<>();
}
