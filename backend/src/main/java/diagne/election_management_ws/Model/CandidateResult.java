package diagne.election_management_ws.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CandidateResult implements Comparable<CandidateResult>
{
    private String firstName;
    private String lastName;
    private int voteNumber;
    private String electorNumber;

    @Override
    public boolean equals(Object o)
    {
        CandidateResult candidateObject = (CandidateResult) o;

        return this.firstName.equals(candidateObject.firstName) &&
                this.lastName.equals(candidateObject.lastName) &&
                this.electorNumber.equals(candidateObject.electorNumber);
    }

    @Override
    public int compareTo(CandidateResult o) {
        if(this.firstName.equals(o.firstName) &&
                this.lastName.equals(o.lastName) &&
                this.electorNumber.equals(o.electorNumber))
            return 0;
        else
            return -1;
    }
}
