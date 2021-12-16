package diagne.election_management_ws.Entities.Circonscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "circonscription",path = "circonscription")
public interface CirconscriptionRepository extends JpaRepository<Circonscription, Long>
{

}
