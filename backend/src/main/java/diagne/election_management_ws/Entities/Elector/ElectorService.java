package diagne.election_management_ws.Entities.Elector;

import diagne.election_management_ws.Entities.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ElectorService implements UserDetailsService
{
    private final ElectorRepository electorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ElectorService(ElectorRepository electorRepository,
                          PasswordEncoder encoder)
    {
        this.electorRepository = electorRepository;
        this.passwordEncoder = encoder;
    }

    public Elector getElectorByNumber(String electorNumber)
    {
        return this.electorRepository.getElectorByElectorNumber(electorNumber);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return (UserDetails) this.electorRepository.getElectorByElectorNumber(username);
    }

    public Elector save(Elector elector)
    {
        elector.setPassword(this.passwordEncoder.encode(elector.getPassword()));
        if(elector.getRoles().isEmpty())
            elector.setRoles(initializeRoles());
        return this.electorRepository.save(elector);
    }

    private Set<Role> initializeRoles()
    {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(null,"ROLE_ELECTOR"));
        return roles;
    }
}
