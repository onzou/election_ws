package diagne.election_management_ws.Entities.Arrondissement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@RepositoryRestResource(collectionResourceRel = "arrondissement",path = "arrondissement")
@CrossOrigin
public interface ArrondissementRepository extends JpaRepository<Arrondissement, Long>
{
    Arrondissement getArrondissementByName(String name);
}