package diagne.election_management_ws.Entities.VoteOffice;

import diagne.election_management_ws.Entities.Arrondissement.ArrondissementService;
import org.springframework.stereotype.Service;
import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import java.util.List;
import java.util.Optional;

@Service
public class VoteOfficeService
{
    private final VoteOfficeRepository voteOfficeRepo;
    private final ArrondissementService arrondissementService;

    public VoteOfficeService(VoteOfficeRepository voteOfficeRepo,
                             ArrondissementService arrondissementService)
    {
        this.voteOfficeRepo = voteOfficeRepo;
        this.arrondissementService = arrondissementService;
    }

    public List<VoteOffice> saveAll(List<VoteOffice> voteOfficeList)
    {
        return this.voteOfficeRepo.saveAll(voteOfficeList);
    }

    public VoteOffice getVoteOfficeByNameAndArrondissement(String voteOfficename, String arrondissementName)
    {
        Arrondissement arrondissement = this.arrondissementService.getArrondissementByName(arrondissementName);
        return this.voteOfficeRepo.getVoteOfficeByNameAndArrondissement(voteOfficename,arrondissement.getId()).get();
    }
}
