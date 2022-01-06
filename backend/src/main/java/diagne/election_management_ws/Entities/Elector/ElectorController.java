package diagne.election_management_ws.Entities.Elector;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "elector")
@CrossOrigin
public class ElectorController
{
    private final ElectorService electorService;

    public ElectorController(ElectorService electorService)
    {
        this.electorService = electorService;
    }

    @PostMapping(path = "login")
    public ResponseEntity<Object> login(Elector elector)
    {
        Elector electorLoggedIn = (Elector) electorService.loadUserByUsername(elector.getElectorNumber());
        return ResponseEntity.ok(electorLoggedIn);
    }

    @PostMapping(path = "signup")
    public ResponseEntity<Object> signup(@ModelAttribute Elector elector)
    {
        return ResponseEntity.created(URI.create("elector"))
                             .body(this.electorService.save(elector));
    }

    @PostMapping(path = "modify")
    public ResponseEntity<Object> modify(@RequestBody Elector elector)
    {
        this.electorService.modify(elector);
        return ResponseEntity.ok(null);
    }

    @GetMapping(path = "candidate")
    public ResponseEntity<List<Elector>> getCandidates()
    {
        return ResponseEntity.ok(this.electorService.getAllCandidates());
    }


}
