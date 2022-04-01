package diagne.election_management_ws.Entities.Elector;

import diagne.election_management_ws.Entities.Token.TokenService;
import diagne.election_management_ws.Model.SubscriptionModel;
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
    private final TokenService tokenService;

    public ElectorController(ElectorService electorService,
                             TokenService tokenService)
    {
        this.electorService = electorService;
        this.tokenService = tokenService;
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

    @PostMapping(path = "logout")
    public ResponseEntity<Object> logout(@RequestHeader("Authorization") String token)
    {
        this.tokenService.deleteTokenByValue(this.getTokenFromHeader(token));
        return ResponseEntity.ok("Déconnexion réussie!");
    }

    @PostMapping(path = "modify")
    public ResponseEntity<Object> modify(@RequestBody Elector elector)
    {
        this.electorService.modify(elector);
        return ResponseEntity.ok(null);
    }

    @PostMapping(path = "subscribe")
    public ResponseEntity<Object> subscribeUser(@RequestBody SubscriptionModel subscriptionModel)
    {
        return ResponseEntity.ok(this.electorService.subscribeUser(subscriptionModel));
    }

    @GetMapping(path = "candidate")
    public ResponseEntity<List<Elector>> getCandidates()
    {
        return ResponseEntity.ok(this.electorService.getAllCandidates());
    }

    @PostMapping(path = "vote-area-change")
    public ResponseEntity<Object> changeVoteArea(@RequestBody Elector elector,
                                                 @RequestParam("region") String regionName,
                                                 @RequestParam("arrondissement") String arrondissementName,
                                                 @RequestParam("voteOffice") String voteOfficeName)
    {
        this.electorService.changeVoteArea(elector,regionName,arrondissementName,voteOfficeName);
        return ResponseEntity.ok("Changement de lieu de vote effectué avec succès");
    }

    @PostMapping(path = "save")
    public ResponseEntity<Object> save(@RequestBody List<Elector> electors)
    {
        this.electorService.saveAll(electors);
        return ResponseEntity.ok(null);
    }

    @GetMapping(path = "get/{id}")
    public ResponseEntity<Object> getElectorById(@PathVariable("id") Long electorId)
    {
        return ResponseEntity.ok(this.electorService.getElectorById(electorId));
    }

    @GetMapping(path = "areas")
    public ResponseEntity<Object> getCirconscriptions()
    {
        return ResponseEntity.ok(this.electorService.getAreas());
    }

    @PostMapping(path = "check-user-subscription")
    public ResponseEntity<Object> checkUserSubscription(@RequestBody Elector elector)
    {
        return ResponseEntity.ok(this.electorService.checkUserSubscription(elector));
    }

    private String getTokenFromHeader(String token)
    {
        return token.substring("Bearer ".length());
    }

}
