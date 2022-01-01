package diagne.election_management_ws.Entities.Department;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "department",path = "department")
public interface DepartmentRepository extends MongoRepository<Department,String>
{

}
