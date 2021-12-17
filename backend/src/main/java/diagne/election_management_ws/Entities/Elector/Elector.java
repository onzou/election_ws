package diagne.election_management_ws.Entities.Elector;

import diagne.election_management_ws.Entities.Role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    @NotBlank(message = "La résidence est requise")
    private String address;

   // @NotBlank(message = "La date de naissance est obligatoire")
    private Date birthday;

    @NotBlank(message = "Le lieu de naissance est obligatoire")
    private String birthPlace;

    @NotBlank(message = "Le numéro de la carte d'identité est obligatoire")
    private String cni;

    //elector card credentials
    private String electorNumber;
    private String region;
    private String department;
    private String arrondissement;
    private String town;

    @NotBlank(message = "Assurez-vous d'avoir renseigné votre lieu de vote")
    private String voteArea;

    private String voteOffice;

    private boolean isCandidate = false;

    //security
    private Set<Role> roles = new HashSet<>();
}
