package diagne.election_management_ws.Entities.Token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "token",path = "token")
public interface TokenRepository extends JpaRepository<Token,Long>
{
    List<Token> findAllByValue(String value);
}
