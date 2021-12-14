package diagne.election_management_ws.Entities.VoteArea;

import diagne.election_management_ws.Entities.VoteOffice.VoteOffice;
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
public class VoteArea extends Area
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private Set<VoteOffice> voteOffices = new HashSet<>();
}
