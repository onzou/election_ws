package diagne.election_management_ws.Entities.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "department",path = "department")
public interface DepartmentRepository extends JpaRepository<Department, Long>
{

    @Query(value = "SELECT * FROM department WHERE name = :name",nativeQuery = true)
    Department getByName(@Param("name") String name);

    List<Department> getDepartmentByRegionId(Long regionId);
}