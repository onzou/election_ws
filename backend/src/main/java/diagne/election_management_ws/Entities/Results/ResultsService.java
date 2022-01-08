package diagne.election_management_ws.Entities.Results;

import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import diagne.election_management_ws.Entities.Arrondissement.ArrondissementService;
import diagne.election_management_ws.Entities.Department.Department;
import diagne.election_management_ws.Entities.Department.DepartmentService;
import diagne.election_management_ws.Entities.Elector.Elector;
import diagne.election_management_ws.Entities.Elector.ElectorService;
import diagne.election_management_ws.Entities.Region.Region;
import diagne.election_management_ws.Entities.Region.RegionService;
import diagne.election_management_ws.Entities.Vote.Vote;
import diagne.election_management_ws.Entities.Vote.VoteService;
import diagne.election_management_ws.Entities.VoteOffice.VoteOffice;
import diagne.election_management_ws.Model.Results;
import diagne.election_management_ws.Model.CandidateResult;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        List<Results> finalResults = new ArrayList<>();

        //avoir tous les départements
        List<Region> regions = this.regionService.getAllRegions();
        List<Results> resultsDepartments = this.getResultsInDepartments();
        int nbreVotants = 0;
        int nbreInscrits = 0;
        int bulletinNul = 0;
        int voteNumber;
        String firstName = "", lastName = "";
        //pour chaque département ...
        for(Region region: regions)
        {
            List<Department> departmentsInCurrentRegion = this.departmentService.getAllInSpecificRegion(region.getId());
            List<String> departmentNames = departmentsInCurrentRegion.stream()
                    .map(department -> department.getName())
                    .collect(Collectors.toList());

            List<Results> departmentsResultsInCurrentRegion = resultsDepartments.stream()
                    .filter(departmentResult ->
                    {
                        return departmentNames.contains(departmentResult.getName());
                    }).collect(Collectors.toList());
            
            nbreVotants = 0;
            nbreInscrits = 0;
            bulletinNul = 0;

            Results singleRegionResult = new Results();
            List<CandidateResult> candidateResults = new ArrayList<>();
            for (Results results1: departmentsResultsInCurrentRegion)
            {
                nbreVotants += results1.getVotant();
                nbreInscrits += results1.getNombreInscrits();
                bulletinNul += results1.getBulletinNul();

                candidateResults.addAll(results1.getCandidates());

            }
            singleRegionResult.setVotant(nbreVotants);
            singleRegionResult.setName(region.getName());
            singleRegionResult.setNombreInscrits(nbreInscrits);
            singleRegionResult.setBulletinNul(bulletinNul);

            List<CandidateResult> candidateResultsInCurrentRegion = new ArrayList<>();
            for (CandidateResult candidateResult: candidateResults)
            {
                if(!candidateResultsInCurrentRegion.contains(candidateResult))
                {
                    candidateResultsInCurrentRegion.add(candidateResult);
                }else
                {
                    int index = candidateResultsInCurrentRegion.indexOf(candidateResult);
                    CandidateResult tmpResult = candidateResultsInCurrentRegion.get(index);
                    tmpResult.setVoteNumber(tmpResult.getVoteNumber() + candidateResult.getVoteNumber());
                    candidateResultsInCurrentRegion.set(index,tmpResult);
                }
            }
            singleRegionResult.setVotant(nbreVotants);
            singleRegionResult.setBulletinNul(bulletinNul);
            singleRegionResult.setNombreInscrits(nbreInscrits);
            singleRegionResult.setCandidates(candidateResultsInCurrentRegion);
            finalResults.add(singleRegionResult);
        }

        return finalResults;
    }



    public List<Results> getResultsInDepartments()
    {
        List<Results> finalResults = new ArrayList<>();

        //avoir tous les départements
        List<Department> departments = this.departmentService.getAllDepartments();
        List<Results> resultsArrondissements = this.getResultsInArrondissements();
        int nbreVotants = 0;
        int nbreInscrits = 0;
        int bulletinNul = 0;
        int voteNumber;
        String firstName = "", lastName = "";
        //pour chaque département ...
        for(Department department: departments)
        {
            List<Arrondissement> arrondissementsInCurrentDepartment = this.arrondissementService.getAllInSpecificDepartment(department.getId());
            List<String> arrondissementNames = arrondissementsInCurrentDepartment.stream()
                                                .map(arrondissement -> arrondissement.getName())
                                                .collect(Collectors.toList());

            List<Results> arrondissementsResultsInCurrentDepartment = resultsArrondissements.stream()
                                .filter(arrondissementResult ->
                                {
                                    return arrondissementNames.contains(arrondissementResult.getName());
                                }).collect(Collectors.toList());
            nbreVotants = 0;
            nbreInscrits = 0;
            bulletinNul = 0;

            Results singleRegionResult = new Results();
            List<CandidateResult> candidateResults = new ArrayList<>();
            for (Results results1: arrondissementsResultsInCurrentDepartment)
            {
                nbreVotants += results1.getVotant();
                nbreInscrits += results1.getNombreInscrits();
                bulletinNul += results1.getBulletinNul();

                candidateResults.addAll(results1.getCandidates());

            }
            singleRegionResult.setVotant(nbreVotants);
            singleRegionResult.setName(department.getName());
            singleRegionResult.setNombreInscrits(nbreInscrits);
            singleRegionResult.setBulletinNul(bulletinNul);

            List<CandidateResult> candidateResultsInCurrentRegion = new ArrayList<>();
            for (CandidateResult candidateResult: candidateResults)
            {
                if(!candidateResultsInCurrentRegion.contains(candidateResult))
                {
                    candidateResultsInCurrentRegion.add(candidateResult);
                }else
                {
                    int index = candidateResultsInCurrentRegion.indexOf(candidateResult);
                    CandidateResult tmpResult = candidateResultsInCurrentRegion.get(index);
                    tmpResult.setVoteNumber(tmpResult.getVoteNumber() + candidateResult.getVoteNumber());
                    candidateResultsInCurrentRegion.set(index,tmpResult);
                }
            }
            singleRegionResult.setVotant(nbreVotants);
            singleRegionResult.setBulletinNul(bulletinNul);
            singleRegionResult.setNombreInscrits(nbreInscrits);
            singleRegionResult.setCandidates(candidateResultsInCurrentRegion);
            finalResults.add(singleRegionResult);
        }

        return finalResults;
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
                    candidateResult.setElectorNumber(candidate.getElectorNumber());
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

    public Arrondissement getResultsInSpecificArrondissement(String name)
    {
//        Arrondissement arrondissement = this.arrondissementService.getArrondissementByName(name);
        return this.arrondissementService.getArrondissementByName(name);

//        Set<VoteOffice> voteOffices = arrondissement.getVoteOffices();
//        Results arrondissementResult = new Results();
//        int nbreVotants = 0;
//        for(VoteOffice voteOffice: voteOffices)
//        {
//            List<Vote> votes = this.voteService.getVotesByVoteOffice(voteOffice.getId());
//            nbreVotants += votes.size();
//        }
//        return nbreVotants;
    }

    public Department getResultsInSpecificDepartment(String name)
    {
        return this.departmentService.getDepartmentByName(name);
    }
}
