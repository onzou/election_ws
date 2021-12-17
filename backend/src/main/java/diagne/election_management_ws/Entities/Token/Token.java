package diagne.election_management_ws.Entities.Token;

import diagne.election_management_ws.Entities.Elector.Elector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token
{
    @Id
    private String id;
    private String value;
    private String electorNumber;
}
