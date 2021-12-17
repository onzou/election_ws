package diagne.election_management_ws.Entities.VotersList;

import diagne.election_management_ws.Entities.Elector.Elector;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "voters_list",path = "voters_list")
public interface VotersListRepository extends MongoRepository<VotersList,String>
{
    Optional<VotersList> getVotersListByElectors(List<Elector> elector);
}
