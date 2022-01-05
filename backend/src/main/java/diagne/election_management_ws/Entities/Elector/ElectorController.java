package diagne.election_management_ws.Entities.Elector;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
