package diagne.election_management_ws.Entities.Town;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "town",path = "town")
public interface TownRepository extends MongoRepository<Town,String>
{

}
