package diagne.election_management_ws.Entities.Token;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "token",path = "token")
public interface TokenRepository extends MongoRepository<Token,String>
{
    List<Token> findAllByValue(String value);
}
