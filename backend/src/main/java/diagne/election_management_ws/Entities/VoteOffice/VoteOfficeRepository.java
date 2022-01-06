package diagne.election_management_ws.Entities.VoteOffice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "vote_office",path = "vote_office")
public interface VoteOfficeRepository extends JpaRepository<VoteOffice,Long>
{

    List<VoteOffice> findVoteOfficeByArrondissement_Id(Long id);

    @Query(value = "select * from vote_office where arrondissement_id = :arrondissementId and name = :voteOfficeName",
            nativeQuery = true)
    Optional<VoteOffice> getVoteOfficeByNameAndArrondissement(@Param("voteOfficeName") String voteOfficeName,
                                                  @Param("arrondissementId") Long arrondissementId);
}
