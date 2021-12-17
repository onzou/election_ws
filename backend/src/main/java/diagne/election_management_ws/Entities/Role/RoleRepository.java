package diagne.election_management_ws.Entities.Role;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "role",path = "role")
public interface RoleRepository extends MongoRepository<Role,String>
{

}
