package diagne.election_management_ws.Entities.Elector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "elector")
public class Elector
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
