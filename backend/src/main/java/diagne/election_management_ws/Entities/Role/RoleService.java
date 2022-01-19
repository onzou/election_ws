package diagne.election_management_ws.Entities.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService 
{
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByValue(String roleValue)
    {
        return this.roleRepository.getRoleByValue(roleValue);
    }
}