package diagne.election_management_ws.Entities.VotersList;

import diagne.election_management_ws.Entities.Elector.Elector;
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

    @Column(name = "voters_list_identification",unique = true)
    private String identification;

    @OneToMany
    private Set<Elector> electors = new HashSet<>();
}
