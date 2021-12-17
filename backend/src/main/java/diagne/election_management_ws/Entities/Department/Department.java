package diagne.election_management_ws.Entities.Department;

import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import diagne.election_management_ws.Model.Classes.Circonscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Document()
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department extends Circonscription
{
    @Id
    private String id;
    private Set<Arrondissement> arrondissements = new HashSet<>();
}
