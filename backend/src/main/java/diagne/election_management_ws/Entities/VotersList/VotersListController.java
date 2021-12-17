package diagne.election_management_ws.Entities.VotersList;

import diagne.election_management_ws.Entities.Elector.Elector;
import diagne.election_management_ws.Entities.VoteOffice.VoteOfficeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "voters_list")
@RestController
public class VotersListController
{
    private final VotersListService votersListService;


    public VotersListController(VotersListService votersListService)
    {
        this.votersListService = votersListService;
    }

    @PostMapping(path = "check_subscription")
    public ResponseEntity<VotersList> checkSubscription(@RequestBody Elector elector)
    {
        return ResponseEntity.ok(this.votersListService.checkSubscription(elector));
    }

    @PostMapping(path = "subscribe")
    public ResponseEntity<Object> subscribe(@RequestBody Elector elector)
    {
        return ResponseEntity.ok(null);
    }

}
