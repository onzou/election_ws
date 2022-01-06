package diagne.election_management_ws.Entities.Vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import diagne.election_management_ws.Entities.Elector.Elector;
import diagne.election_management_ws.Entities.VoteOffice.VoteOffice;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = true)
    private Elector candidate;

    @Column(name = "vote_number",nullable = false)
    private int voteNumber = 1;

    @OneToOne(optional = false)
    @JsonIgnore
    private Elector voter;

    @ManyToOne
    private VoteOffice voteOffice;
}

