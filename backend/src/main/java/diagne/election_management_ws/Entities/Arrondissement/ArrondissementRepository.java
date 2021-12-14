package diagne.election_management_ws.Entities.Arrondissement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "arrondissement",path = "arrondissement")
public interface ArrondissementRepository extends JpaRepository<Arrondissement, Long>
{

}