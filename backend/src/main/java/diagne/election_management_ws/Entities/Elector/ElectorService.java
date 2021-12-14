package diagne.election_management_ws.Entities.Elector;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ElectorService implements UserDetailsService
{
    private final ElectorRepository electorRepository;

    public ElectorService(ElectorRepository electorRepository)
    {
        this.electorRepository = electorRepository;
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
}
