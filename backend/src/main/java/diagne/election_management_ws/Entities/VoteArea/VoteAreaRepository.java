package diagne.election_management_ws.Entities.VoteArea;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "vote_area",path = "vote_area")
public interface VoteAreaRepository extends MongoRepository<VoteArea,String>
{

}