package diagne.election_management_ws.Model;

import diagne.election_management_ws.Entities.Elector.Elector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Results
{
    private String name;
    private int votant;
    private int nombreInscrits;
    private int bulletinNul;
    private List<CandidateResult> candidates = new ArrayList<>();
}
