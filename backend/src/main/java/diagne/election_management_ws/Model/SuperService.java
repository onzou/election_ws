package diagne.election_management_ws.Model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SuperService<Entity extends IEntityModel, Repo extends SuperRepository<Entity>>
{
    protected final Repo repo;

    @Autowired
    public SuperService(Repo repo)
    {
        this.repo = repo;
    }

    //creating
    public Entity save(Entity entityToSave)
    {
        return this.repo.save(entityToSave);
    }

    public List<Entity> saveAll(List<Entity> entities)
    {
        return this.repo.saveAll(entities);
    }

    //reading
    public Entity getById(Long id)
    {
        return this.repo.getById(id);
    }

    public List<Entity> getAll()
    {
        return this.repo.findAll();
    }
}
