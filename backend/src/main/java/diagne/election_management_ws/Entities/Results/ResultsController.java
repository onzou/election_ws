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
        return ResponseEntity.ok(this.resultsService.getResultsInRegions());
    }

    @GetMapping(path = "department")
    public ResponseEntity<Object> getResultsInDepartments()
    {
        return ResponseEntity.ok(this.resultsService.getResultsInDepartments());
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

    @GetMapping(path = "department/{name}")
    public ResponseEntity<Object> getResultsInSpecificDepartment(@PathVariable String name)
    {
        return ResponseEntity.ok(this.resultsService.getResultsInSpecificDepartment(name));
    }
}
