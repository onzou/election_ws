package diagne.election_management_ws.Entities.Department;

import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import diagne.election_management_ws.Entities.Region.Region;
import diagne.election_management_ws.Model.Area;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class Department
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Region region;

    @OneToMany
    private Set<Arrondissement> arrondissements = new HashSet<>();
}


