package diagne.election_management_ws.Entities.Results;

import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import diagne.election_management_ws.Entities.Arrondissement.ArrondissementService;
import diagne.election_management_ws.Entities.Department.Department;
import diagne.election_management_ws.Entities.Department.DepartmentService;
import diagne.election_management_ws.Entities.Elector.Elector;
import diagne.election_management_ws.Entities.Elector.ElectorService;
import diagne.election_management_ws.Entities.Region.RegionService;
import diagne.election_management_ws.Entities.Vote.Vote;
import diagne.election_management_ws.Entities.Vote.VoteService;
import diagne.election_management_ws.Entities.VoteOffice.VoteOffice;
import diagne.election_management_ws.Model.Results;
import diagne.election_management_ws.Model.CandidateResult;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResultsService
{
    private final RegionService regionService;
    private final DepartmentService departmentService;
    private final ArrondissementService arrondissementService;
    private final VoteService voteService;
    private final ElectorService electorService;

    public ResultsService(RegionService regionService,
                          DepartmentService departmentService,
                          ArrondissementService arrondissementService,
                          VoteService voteService,
                          ElectorService electorService)
    {
        this.regionService = regionService;
        this.departmentService = departmentService;
        this.arrondissementService = arrondissementService;
        this.voteService = voteService;
        this.electorService = electorService;
    }

    public Object getResultsInRegions()
    {
        return null;
    }

    public Object getResultsInDepartments()
    {
        List<Results> finalResults = new ArrayList<>();

        //avoir tous les départements
        List<Department> departments = this.departmentService.getAllDepartments();
        List<Results> resultsArrondissements = this.getResultsInArrondissements();
        //pour chaque département ...
//        for(Department department: departments)
//        {
//            Results results = new Results();
//            Arrondissement[] arrondissementsInCurrentDepartment = (Arrondissement[]) department.getArrondissements().toArray();
//
//            List<Results> arrondissementsResultsInCurrentDepartment = new ArrayList<>();
//            for(int i = 0; i < resultsArrondissements.size(); i++)
//            {
//                if(arrondissementsInCurrentDepartment[i].getName())
//            }
//        }
//        List<Results> arrondissementResults = this.getResultsInArrondissements()
//                .stream()
//                .filter(arrondissementResult -> arrondissementResult.getName())
        return null;
    }

    public List<Results> getResultsInArrondissements()
    {
        List<Arrondissement> arrondissements = this.arrondissementService.getAll();
        HashMap<String,Object> allArrondissementsResults = new HashMap<>();
        List<Elector> candidates = this.electorService.getAllCandidates();
        List<Results> finalResults = new ArrayList<>();

//        List<Arrondissement>
        for (Arrondissement next : arrondissements)
        {
            Set<VoteOffice> voteOffices = next.getVoteOffices();
            Results arrondissementResult = new Results();
            int nbreInscrit = 0;
            int nbreVotants = 0;
            int bulletinNul = 0;
            for(VoteOffice voteOffice: voteOffices)
            {
                List<Vote> votes = this.voteService.getVotesByVoteOffice(voteOffice.getId());
                nbreVotants += votes.size();
                nbreInscrit += voteOffice.getVotersList().getElectors().size();
                List<CandidateResult> candidateResults = new ArrayList<>();
                for(Elector candidate: candidates)
                {
                    CandidateResult candidateResult = new CandidateResult();
                    candidateResult.setFirstName(candidate.getFirstName());
                    candidateResult.setLastName(candidate.getLastName());
                    candidateResult.setVoteNumber(this.voteService.getNumberOfVotesByCandidateInArrondissement(candidate.getId(),next.getId()));
                    candidateResults.add(candidateResult);
                }
                //créer le arroylist des résultats candidats:
                bulletinNul += this.voteService.getBulletinNulByVoteOffice(voteOffice.getId());
                arrondissementResult.setCandidates(candidateResults);
            }
            arrondissementResult.setVotant(nbreVotants);
            arrondissementResult.setNombreInscrits(nbreInscrit);
            arrondissementResult.setBulletinNul(bulletinNul);
            arrondissementResult.setName(next.getName());
            finalResults.add(arrondissementResult);
//            allArrondissementsResults.put(next.getName(),arrondissementResult);
        }
        return finalResults;
    }

    public int getResultsInSpecificArrondissement(String name)
    {
        Arrondissement arrondissement = this.arrondissementService.getArrondissementByName(name);
        Set<VoteOffice> voteOffices = arrondissement.getVoteOffices();
        Results arrondissementResult = new Results();
        int nbreVotants = 0;
        for(VoteOffice voteOffice: voteOffices)
        {
            List<Vote> votes = this.voteService.getVotesByVoteOffice(voteOffice.getId());
            nbreVotants += votes.size();
        }
        return nbreVotants;
    }
}
