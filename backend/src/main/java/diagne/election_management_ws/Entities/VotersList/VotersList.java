package diagne.election_management_ws.Entities.VotersList;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "voters_list_name")
    private String name;

    @OneToOne
    private Elector representative;

    @Column(name = "voters_list_identification",unique = true)
    private String identification;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Elector> electors = new HashSet<>();

    @OneToOne(mappedBy = "votersList", fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private VoteOffice voteOffice;
}
