package diagne.election_management_ws.Entities.Region;

import diagne.election_management_ws.Entities.Department.Department;
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
@Document(collection = "region")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region extends Circonscription
{
    @Id
    private String id;
    private Set<Department> departments = new HashSet<>();
}
