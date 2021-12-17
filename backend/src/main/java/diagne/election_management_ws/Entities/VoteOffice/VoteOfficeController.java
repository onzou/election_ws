package diagne.election_management_ws.Entities.VoteOffice;

import diagne.election_management_ws.Entities.Elector.Elector;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteOfficeController
{
    private final VoteOfficeService voteOfficeService;


    public VoteOfficeController(VoteOfficeService voteOfficeService)
    {
        this.voteOfficeService = voteOfficeService;
    }


}
