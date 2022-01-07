package diagne.election_management_ws.Entities.Vote;

import diagne.election_management_ws.Entities.Elector.Elector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@CrossOrigin
//@RepositoryRestResource(collectionResourceRel = "vote",path = "vote")
public interface VoteRepository extends JpaRepository<Vote,Long>
{
    Optional<Vote> getVoteByCandidate_IdAndVoteOffice_Id(Long electorId, Long voteOfficeId);

    @Modifying
    @Query(value = "UPDATE vote SET vote_number = vote_number + 1 where id = :voteId",nativeQuery = true)
    void increaseNumberVote(@Param("voteId") Long voteId);

    @Query(value = "SELECT * FROM vote WHERE vote_office_id = :id",nativeQuery = true)
    List<Vote> getVotesByVoteOfficeId(@Param("id") Long id);

    @Query(value = "SELECT count(*) FROM vote WHERE candidate_id = :candidateId",nativeQuery = true)
    int getNumberOfVotesByCandidateId(@Param("candidateId") Long candidateId);

    @Query(value = "SELECT count(*) FROM vote WHERE candidate_id = null", nativeQuery = true)
    int getBulletinNulByVoteOffice(Long id);

    @Query(value = "select count(*) from vote, arrondissement_vote_offices as arr_vo " +
            "where vote.vote_office_id = arr_vo.vote_offices_id and arr_vo.arrondissement_id = :arrondissementId " +
            "and vote.candidate_id = :candidateId", nativeQuery = true)
    int getNumberOfVotesByCandidateIdAndArrondissementId(@Param("candidateId") Long candidateId,@Param("arrondissementId") Long arrondissementId);
}
