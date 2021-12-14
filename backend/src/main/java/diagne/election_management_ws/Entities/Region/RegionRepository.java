package diagne.election_management_ws.Entities.Region;

import diagne.election_management_ws.Entities.Department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "region",path = "region")
public interface RegionRepository extends JpaRepository<Region, Long>
{

}

