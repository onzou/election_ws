package diagne.election_management_ws.Entities.VotersList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "voters_list")
public class VotersListController
{
    private final VotersListService votersListService;

    public VotersListController(VotersListService votersListService)
    {
        this.votersListService = votersListService;
    }

    @PostMapping(path = "all")
    public ResponseEntity<List<VotersList>> saveAll(@RequestBody List<VotersList> votersLists)
    {
        return ResponseEntity.ok(this.votersListService.saveAll(votersLists));
    }
}
