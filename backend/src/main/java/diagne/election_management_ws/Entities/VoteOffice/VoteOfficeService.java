package diagne.election_management_ws.Entities.VoteOffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteOfficeService
{
    private final VoteOfficeRepository voteOfficeRepo;

    @Autowired
    public VoteOfficeService(VoteOfficeRepository voteOfficeRepository)
    {
        this.voteOfficeRepo = voteOfficeRepository;
    }


}
