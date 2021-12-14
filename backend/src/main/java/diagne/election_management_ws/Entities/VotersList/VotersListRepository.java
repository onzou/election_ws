package diagne.election_management_ws.Entities.VotersList;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "voters_list",path = "voters_list")
public interface VotersListRepository
{

}
