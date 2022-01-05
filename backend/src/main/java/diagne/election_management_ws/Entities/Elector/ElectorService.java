package diagne.election_management_ws.Entities.Elector;

import diagne.election_management_ws.Entities.File.FileService;
import diagne.election_management_ws.Entities.Role.Role;
import diagne.election_management_ws.Entities.Role.RoleService;
import diagne.election_management_ws.Entities.VotersList.VotersList;
import diagne.election_management_ws.Entities.VotersList.VotersListService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ElectorService implements UserDetailsService
{
    private final ElectorRepository electorRepository;
    private final RoleService roleService;
    private final FileService fileService;
    private final PasswordEncoder passwordEncoder;
    private final VotersListService votersListService;

    public ElectorService(ElectorRepository electorRepository,
                          RoleService roleService,
                          FileService fileService,
                          PasswordEncoder passwordEncoder,
                          VotersListService votersListService)
    {
        this.electorRepository = electorRepository;
        this.roleService = roleService;
        this.fileService = fileService;
        this.passwordEncoder = passwordEncoder;
        this.votersListService = votersListService;

    }

    public Elector getElectorByNumber(String electorNumber)
    {
        return this.electorRepository.getElectorByElectorNumber(electorNumber);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Elector elector = this.electorRepository.getElectorByElectorNumber(username);
        if(elector == null)
            throw new UsernameNotFoundException(String.format("Numéro carte électeur %s introuvable",username));
//        if(elector.getRoles().isEmpty())
//            elector.getRoles().add(new Role(null,"ROLE_ELECTOR"));
//        el
        Set<SimpleGrantedAuthority> authorities = elector.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getValue()))
                .collect(Collectors.toSet());


        return new org.springframework.security.core.userdetails.User(elector.getElectorNumber(),elector.getPassword(),authorities);
    }
    
    public Elector save(@ModelAttribute Elector elector)
    {
        Elector savedElector = null;
        //vérifier que l'électeur a bien 18 ans done
        if(Period.between(elector.getBirthday(),LocalDate.now()).getYears() < 18)
            throw new ElectorException("Vous n'êtes en mesure de vous inscrire");

        //avoir la liste électorale correspondant à ses attributs arrondissement et bureau de vote
        VotersList votersListRetrieved = this.votersListService
                    .getVoters_listByVoteOfficeAndArrondissement(elector.getVoteOffice(),elector.getArrondissement());
        //l'ajouter sur la liste électorale obtenue si existante; sinon crée la liste électorale correspondante
        Set<Elector> electorsOfVotersList = votersListRetrieved.getElectors();

        if(electorsOfVotersList.contains((Elector)elector))
            throw new ElectorException("Vous vous êtes déjà inscrit");
        else
        {
            if(elector.getPicture() != null)
            {
                String fileURI = this.fileService.uploadFile(elector.getPicture());
                elector.setPicturePath(fileURI);
            }
            elector.setPassword(this.passwordEncoder.encode(elector.getPassword()));
            if(elector.getRoles() == null || elector.getRoles().isEmpty())
            {
                Set<Role> roles = new HashSet<>();
                Role role = this.roleService.getRoleByValue("ROLE_ELECTOR");
                roles.add(role);
                elector.setRoles(roles);
            }
            savedElector = this.electorRepository.save(elector);
            votersListRetrieved.getElectors().add(savedElector);

            this.votersListService.save(votersListRetrieved);
        }

        return savedElector;
    }

    public void modify(Elector elector)
    {
        Set<Role> roles = new HashSet<>();
        if(elector.isCandidate())
        {
            Role role = this.roleService.getRoleByValue("ROLE_CANDIDATE");
            roles.add(role);
            elector.setRoles(roles);
        }
        else
        {
            Role role = this.roleService.getRoleByValue("ROLE_ELECTOR");
            roles.add(role);
            elector.setRoles(roles);
        }
        elector.setPassword(this.passwordEncoder.encode(elector.getPassword()));
        this.electorRepository.save(elector);
    }
}
