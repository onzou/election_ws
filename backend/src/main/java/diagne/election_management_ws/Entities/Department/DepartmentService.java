package diagne.election_management_ws.Entities.Department;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService
{
    private final DepartmentRepository departmentRepo;

    public DepartmentService(DepartmentRepository departmentRepo)
    {
        this.departmentRepo = departmentRepo;
    }

    public List<Department> getAllDepartments()
    {
        return this.departmentRepo.findAll();
    }

    public Department getDepartmentByName(String name)
    {
        return this.departmentRepo.getByName(name);
    }

    public List<Department> getAllInSpecificRegion(Long regionId)
    {
        return this.departmentRepo.getDepartmentByRegionId(regionId);
    }
}
