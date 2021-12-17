package diagne.election_management_ws.Entities.Vote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import diagne.election_management_ws.Entities.Elector.Elector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote
{
    @Id
    private String id;

    private boolean isValid = true;
    private Elector candidate;

    @JsonIgnore
    private Elector voter;
}

