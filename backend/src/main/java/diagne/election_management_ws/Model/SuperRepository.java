package diagne.election_management_ws.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SuperRepository<T extends IEntityModel> extends JpaRepository<T,Long>
{

}
