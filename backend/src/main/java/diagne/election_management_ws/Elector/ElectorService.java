package diagne.election_management_ws.Elector;

import diagne.election_management_ws.Model.SuperService;
import org.springframework.stereotype.Service;

@Service
public class ElectorService extends SuperService<Elector,ElectorRepository>
{

    public ElectorService(ElectorRepository electorRepository)
    {
        super(electorRepository);
    }
}
