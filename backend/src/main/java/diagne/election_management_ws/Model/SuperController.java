package diagne.election_management_ws.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuperController<Entity extends IEntityModel, Repo extends SuperRepository<Entity>>
{
    private final SuperService<Entity,Repo> superService;

    @Autowired
    public SuperController(SuperService<Entity,Repo> superService)
    {
        this.superService = superService;
    }

    //add crud operations
}
