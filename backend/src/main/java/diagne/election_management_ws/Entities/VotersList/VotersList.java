package diagne.election_management_ws.Entities.VotersList;

import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import diagne.election_management_ws.Entities.Elector.Elector;
import diagne.election_management_ws.Entities.VoteOffice.VoteOffice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class VotersList
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "voters_list_name")
    private String name;

    @OneToOne
    private Elector representative;

    @Column(name = "voters_list_identification",unique = true)
    private String identification;

    @OneToMany
    private Set<Elector> electors = new HashSet<>();
}
