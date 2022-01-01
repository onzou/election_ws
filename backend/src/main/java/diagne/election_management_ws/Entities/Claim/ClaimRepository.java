package diagne.election_management_ws.Entities.Claim;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "claim",path = "claim")
public interface ClaimRepository extends MongoRepository<Claim, String>
{

}
