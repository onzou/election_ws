package diagne.election_management_ws.Entities.Vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import diagne.election_management_ws.Entities.Elector.Elector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "is_vote_valid")
    private boolean isValid = true;

    @OneToOne(optional = true)
    private Elector candidate;

    @OneToOne(optional = false)
    @JsonIgnore
    private Elector voter;
}

