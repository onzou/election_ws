package diagne.election_management_ws.Model;

import diagne.election_management_ws.Entities.Elector.Elector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor @NoArgsConstructor
public class VoteModel
{
    @NotNull(message = "L'électeur est obligatoire")
    private Elector elector;

    private Elector candidate;
}
