package diagne.election_management_ws.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SummaryResults
{
    private Long nbreInscrits;
    private Long nbreVotants;
    private Long bulletinNuls;
}
