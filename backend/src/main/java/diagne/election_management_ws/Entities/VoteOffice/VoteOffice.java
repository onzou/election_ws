package diagne.election_management_ws.Entities.VoteOffice;

import diagne.election_management_ws.Entities.Vote.Vote;
import diagne.election_management_ws.Entities.VotersList.VotersList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Data
@AllArgsConstructor @NoArgsConstructor
public class VoteOffice
{
    @Id
    private String id;
    private VotersList votersList;

    private Set<Vote> votes = new HashSet<>();
}
