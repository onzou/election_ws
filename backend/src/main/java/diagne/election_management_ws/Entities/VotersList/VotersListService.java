package diagne.election_management_ws.Entities.VotersList;

import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import diagne.election_management_ws.Entities.Arrondissement.ArrondissementService;
import diagne.election_management_ws.Entities.VoteOffice.VoteOffice;
import diagne.election_management_ws.Entities.VoteOffice.VoteOfficeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VotersListService
{
    private final VotersListRepository votersListRepo;
    private final ArrondissementService arrondissementService;
    private final VoteOfficeService voteOfficeService;

    public VotersListService(VotersListRepository votersListRepository,
                             ArrondissementService arrondissementService,
                             VoteOfficeService voteOfficeService)
    {
        this.votersListRepo = votersListRepository;
        this.arrondissementService = arrondissementService;
        this.voteOfficeService = voteOfficeService;
    }

    public List<VotersList> saveAll(List<VotersList> votersLists)
    {
        return this.votersListRepo.saveAll(votersLists);
    }

    public VotersList getVoters_listByVoteOfficeAndArrondissement(String voteOfficeName, String arrondissementName)
    {
        Arrondissement arrondissement = this.arrondissementService.getArrondissementByName(arrondissementName);
        List<VoteOffice> voteOfficesRetrieved = this.voteOfficeService.getVoteOfficesByArrondissement(arrondissement.getId());
        Set<VoteOffice> voteOfficeFiltered = voteOfficesRetrieved.stream()
                                    .filter(voteOffice -> voteOffice.getName().equals(voteOfficeName))
                                    .collect(Collectors.toSet());
        Optional<VoteOffice> voteOfficeOptional = voteOfficeFiltered.stream().findFirst();
        if(voteOfficeOptional.isPresent())
        {
            VoteOffice voteOffice1 = voteOfficeOptional.get();
            return voteOffice1.getVotersList();
        }
        else
            return null;
    }

    public VotersList save(VotersList votersList)
    {
        return this.votersListRepo.saveAndFlush(votersList);
    }
}
