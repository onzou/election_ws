package diagne.election_management_ws.Entities.VoteOffice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import diagne.election_management_ws.Entities.Vote.Vote;
import diagne.election_management_ws.Entities.VotersList.VotersList;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
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

    @Override
    public String toString() {
        return "VoteOffice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", votersList=" + votersList +
                ", votes=" + votes +
                '}';
    }

    @ManyToOne()
    @JsonIgnore
    private Arrondissement arrondissement;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Vote> votes = new HashSet<>();
}
