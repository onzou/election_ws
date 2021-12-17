package diagne.election_management_ws.Entities.Arrondissement;

import diagne.election_management_ws.Entities.Department.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "arrondissement",path = "arrondissement")
public interface ArrondissementRepository extends MongoRepository<Department,String>
{

}