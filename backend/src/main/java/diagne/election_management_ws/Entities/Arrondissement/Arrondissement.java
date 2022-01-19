package diagne.election_management_ws.Entities.Arrondissement;

import diagne.election_management_ws.Entities.Department.Department;
import diagne.election_management_ws.Entities.VoteOffice.VoteOffice;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "arrondissement")
public class Arrondissement
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne()
    private Department department;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<VoteOffice> voteOffices = new HashSet<>();
}
