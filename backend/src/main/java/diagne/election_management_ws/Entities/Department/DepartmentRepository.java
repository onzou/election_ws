package diagne.election_management_ws.Entities.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "department",path = "department")
public interface DepartmentRepository extends JpaRepository<Department, Long>
{

}