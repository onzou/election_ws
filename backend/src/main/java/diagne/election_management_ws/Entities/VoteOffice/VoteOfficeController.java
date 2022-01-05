package diagne.election_management_ws.Entities.VoteOffice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "vote_office")
public class VoteOfficeController
{
    private final VoteOfficeService voteOfficeService;

    public VoteOfficeController(VoteOfficeService voteOfficeService)
    {
        this.voteOfficeService = voteOfficeService;
    }

    @PostMapping(path = "all")
    public ResponseEntity<List<VoteOffice>> saveAll(@RequestBody List<VoteOffice> voteOffices)
    {
        return ResponseEntity.ok(this.voteOfficeService.saveAll(voteOffices));
    }
}
