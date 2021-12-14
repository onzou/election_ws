package diagne.election_management_ws.Entities.Elector;

import org.springframework.stereotype.Service;

@Service
public class ElectorService
{
    private final ElectorRepository electorRepository;

    public ElectorService(ElectorRepository electorRepository)
    {
        this.electorRepository = electorRepository;
    }
}
