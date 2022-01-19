package diagne.election_management_ws.Entities.VotersList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "voters_list",path = "voters_list")
public interface VotersListRepository extends JpaRepository<VotersList,Long>
{
    @Query(value = "select * from voters_list, voters_list_electors as vol_e " +
            "where vol_e.voters_list_id = voters_list.id " +
            "and vol_e.electors_id = :electorId",nativeQuery = true)
    VotersList getVotersListByElectorId(@Param("electorId") Long electorId);
}
