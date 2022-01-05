package diagne.election_management_ws.config.components;

import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import diagne.election_management_ws.Entities.Department.Department;
import diagne.election_management_ws.Entities.Elector.Elector;
import diagne.election_management_ws.Entities.Region.Region;
import diagne.election_management_ws.Entities.VoteOffice.VoteOffice;
import diagne.election_management_ws.Entities.VotersList.VotersList;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
class RestConfig implements RepositoryRestConfigurer
{

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Elector.class);
        config.exposeIdsFor(Region.class);
        config.exposeIdsFor(Department.class);
        config.exposeIdsFor(Arrondissement.class);
        config.exposeIdsFor(VoteOffice.class);
        config.exposeIdsFor(VotersList.class);
    }
}