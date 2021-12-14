package diagne.election_management_ws.Entities.Arrondissement;

import diagne.election_management_ws.Entities.Town.Town;
import diagne.election_management_ws.Model.Area;
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
@Table(name = "arrondissement")
public class Arrondissement extends Area
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private Set<Town> towns = new HashSet<>();
}
