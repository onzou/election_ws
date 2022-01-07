package diagne.election_management_ws.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CandidateResult
{
    private String firstName;
    private String lastName;
    private int voteNumber;
}
