package diagne.election_management_ws.Entities.Results;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "results")
@CrossOrigin
public class ResultsController
{
    private final ResultsService resultsService;

    public ResultsController(ResultsService resultsService)
    {
        this.resultsService = resultsService;
    }

    @GetMapping(path = "region")
    public ResponseEntity<Object> getResultsInRegions()
    {
        return null;
    }

    @GetMapping(path = "department")
    public ResponseEntity<Object> getResultsInDepartments()
    {
        return null;
    }

    @GetMapping(path = "arrondissement")
    public ResponseEntity<Object> getResultsInArrondissements()
    {
        return ResponseEntity.ok(this.resultsService.getResultsInArrondissements());
    }

    @GetMapping(path = "arrondissement/{name}")
    public ResponseEntity<Object> getResultsInSpecificArrondissement(@PathVariable String name)
    {
        return ResponseEntity.ok(this.resultsService.getResultsInSpecificArrondissement(name));
    }
}
