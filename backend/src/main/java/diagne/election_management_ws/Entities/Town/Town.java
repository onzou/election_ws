package diagne.election_management_ws.Entities.Town;

import diagne.election_management_ws.Entities.VoteArea.VoteArea;
import diagne.election_management_ws.Model.Area;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor @NoArgsConstructor
public class Town extends Area
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private Set<VoteArea> voteAreas = new HashSet<>();
}
