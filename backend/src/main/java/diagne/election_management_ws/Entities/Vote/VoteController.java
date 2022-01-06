package diagne.election_management_ws.Entities.Vote;

import diagne.election_management_ws.Entities.Elector.Elector;
import diagne.election_management_ws.Entities.Elector.ElectorService;
import diagne.election_management_ws.Model.ResponseMessage;
import diagne.election_management_ws.Model.VoteModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "vote")
@CrossOrigin
public class VoteController
{
    private final VoteService voteService;
    private final ElectorService electorService;

    public VoteController(VoteService voteService,
                          ElectorService electorService)
    {
        this.voteService = voteService;
        this.electorService = electorService;
    }

    @PostMapping()
    public ResponseEntity<Object> vote(@RequestBody VoteModel voteModel)
    {
        this.voteService.vote(voteModel.getElector(),voteModel.getCandidate());
        return ResponseEntity.ok(new ResponseMessage("Vote efféctué avec succès!"));
    }
}
