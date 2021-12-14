package diagne.election_management_ws.Elector;

import diagne.election_management_ws.Model.SuperController;
import diagne.election_management_ws.Model.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElectorController extends SuperController<Elector,ElectorRepository>
{
    @Autowired
    public ElectorController(SuperService<Elector, ElectorRepository> superService)
    {
        super(superService);
    }
}
