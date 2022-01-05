package diagne.election_management_ws.Entities.Elector;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import diagne.election_management_ws.Entities.Role.Role;
import diagne.election_management_ws.Model.LocalDateDeserializer;
import diagne.election_management_ws.Model.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @NotBlank(message = "Le mot de passe doit être fourni")
    private String password;

    @NotBlank(message = "Le lieu de naissance est obligatoire")
    private String birthPlace;

    @NotBlank(message = "Le numéro de carte d'identité est obligatoire")
    @Column(nullable = false,unique = true)
    private String cni;

    private String region;

    private String department;

    @Transient
    @JsonIgnore
    private MultipartFile picture;

    private String picturePath;

    @NotBlank(message = "L'arrondissement est obligatoire")
    private String arrondissement;

    @NotNull(message = "Vous devez donner votre date de naissance")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    private String voteOffice;

    private boolean isCandidate = false;

    @Column(name = "elector_number",nullable = false,unique = true)
    private String electorNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
}
