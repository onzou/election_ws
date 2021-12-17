package diagne.election_management_ws.Entities.Elector;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "elector",path = "elector")
public interface ElectorRepository extends MongoRepository<Elector, String>
{
    Elector getElectorByElectorNumber(String value);
}
