package diagne.election_management_ws.Entities.VotersList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "voters_list",path = "voters_list")
public interface VotersListRepository extends JpaRepository<VotersList,Long>
{
}
