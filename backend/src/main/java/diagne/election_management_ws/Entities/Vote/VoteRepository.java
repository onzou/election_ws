package diagne.election_management_ws.Entities.Vote;

import diagne.election_management_ws.Entities.Elector.Elector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin
//@RepositoryRestResource(collectionResourceRel = "vote",path = "vote")
public interface VoteRepository extends JpaRepository<Vote,Long>
{
    Optional<Vote> getVoteByCandidate_IdAndVoteOffice_Id(Long electorId, Long voteOfficeId);
}
