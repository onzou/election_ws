package diagne.election_management_ws.Entities.VoteOffice;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteOfficeService
{
    private final VoteOfficeRepository voteOfficeRepo;

    public VoteOfficeService(VoteOfficeRepository voteOfficeRepo)
    {
        this.voteOfficeRepo = voteOfficeRepo;
    }

    public List<VoteOffice> saveAll(List<VoteOffice> voteOfficeList)
    {
        return this.voteOfficeRepo.saveAll(voteOfficeList);
    }

    public Optional<VoteOffice> getVoteOfficeByNameAndArrondissement(String voteOfficename, Long arrondissementId)
    {
        return this.voteOfficeRepo.getVoteOfficeByNameAndArrondissement(voteOfficename,arrondissementId);
    }
}
