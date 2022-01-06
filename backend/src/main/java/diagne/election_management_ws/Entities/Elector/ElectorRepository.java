package diagne.election_management_ws.Entities.Elector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "elector",path = "elector")
public interface ElectorRepository extends JpaRepository<Elector, Long>
{
    Elector getElectorByElectorNumber(String value);

    @Query(value = "SELECT * FROM elector WHERE is_candidate = true",nativeQuery = true)
    List<Elector> findAllCandidates();

    @Modifying()
    @Query(value = "UPDATE elector SET has_voted = true WHERE id = :electorId",nativeQuery = true)
    void updateAfterVoting(@Param("electorId") Long electorId);
}
