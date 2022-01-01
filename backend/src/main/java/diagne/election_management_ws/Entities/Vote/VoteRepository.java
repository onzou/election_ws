package diagne.election_management_ws.Entities.Vote;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "vote",path = "vote")
public interface VoteRepository extends MongoRepository<Vote,String>
{

}
