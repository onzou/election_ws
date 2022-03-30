package diagne.election_management_ws.Entities.VotersList;

import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import diagne.election_management_ws.Entities.Arrondissement.ArrondissementService;
import diagne.election_management_ws.Entities.VoteOffice.VoteOffice;
import diagne.election_management_ws.Entities.VoteOffice.VoteOfficeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        VoteOffice voteOfficeOptional = this.getVoteOfficeByNameAndArrondissement(voteOfficeName,arrondissementName);
        if(voteOfficeOptional != null)
        {
            return voteOfficeOptional.getVotersList();
        }
        else
            return null;
    }

    public VotersList save(VotersList votersList)
    {
        return this.votersListRepo.saveAndFlush(votersList);
    }

    public VoteOffice getVoteOfficeByNameAndArrondissement(String voteOfficeName, String arrondissementName)
    {
        return this.voteOfficeService.getVoteOfficeByNameAndArrondissement(voteOfficeName,arrondissementName);
    }

    public long getTotal()
    {
        return this.votersListRepo.count();
    }

    public VotersList getVotersListByElectorId(Long electorId)
    {
        return this.votersListRepo.getVotersListByElectorId(electorId);
    }
}
