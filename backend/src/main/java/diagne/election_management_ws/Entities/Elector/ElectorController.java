package diagne.election_management_ws.Entities.Elector;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "elector")
public class ElectorController
{
    private final ElectorService electorService;

    public ElectorController(ElectorService electorService)
    {
        this.electorService = electorService;
    }

    @PostMapping(path = "login")
    public ResponseEntity<Object> login(@RequestBody Elector elector)
    {
        Elector electorLoggedIn = (Elector) electorService.loadUserByUsername(elector.getElectorNumber());
        return ResponseEntity.ok(electorLoggedIn);
    }

    @PostMapping(path = "signup")
    public ResponseEntity<Elector> signup(@RequestBody Elector elector)
    {
        return ResponseEntity.created(URI.create("elector").normalize())
                            .body(this.electorService.save(elector));
    }

    @GetMapping(path = "number/{electorNumber}")
    public ResponseEntity<Elector> getElectorByNumber(@PathVariable("electorNumber") String number)
    {
        return ResponseEntity.ok(this.electorService.getElectorByNumber(number));
    }
}
