package diagne.election_management_ws.Entities.Region;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "circonscription",path = "circonscription")
public interface RegionRepository extends MongoRepository<Region, String>
{

}
