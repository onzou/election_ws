package diagne.election_management_ws.Entities.Vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "vote",path = "vote")
public interface VoteRepository extends JpaRepository<Vote,Long>
{

}
