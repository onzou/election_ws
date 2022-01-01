package diagne.election_management_ws.Entities.VoteOffice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "vote_office",path = "vote_office")
public interface VoteOfficeRepository extends MongoRepository<VoteOffice,String>
{

}
