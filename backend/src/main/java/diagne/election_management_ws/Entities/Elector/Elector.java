package diagne.election_management_ws.Entities.Elector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "users")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Elector
{
    @Id
    private String id;

    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;

    @NotBlank(message = "Le prénom est obligatoire")
    private String lastName;

    private String address;

    private String cni;

    private String voteArea;

    private String town;

    private String voteOffice;

    private boolean isCandidate = false;

    private String electorNumber;
}
