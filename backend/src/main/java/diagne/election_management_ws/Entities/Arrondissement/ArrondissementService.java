package diagne.election_management_ws.Entities.Arrondissement;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrondissementService
{
    private final ArrondissementRepository arrondissementRepository;

    public ArrondissementService(ArrondissementRepository arrondissementRepository)
    {
        this.arrondissementRepository = arrondissementRepository;
    }

    public Arrondissement getArrondissementByName(String arrondissementName)
    {
        return this.arrondissementRepository.getArrondissementByName(arrondissementName);
    }

    public List<Arrondissement> getAll()
    {
        return this.arrondissementRepository.findAll();
    }

    public List<Arrondissement> getAllInSpecificDepartment(Long departmentId)
    {
        return this.arrondissementRepository.getArrondissementsByDepartmentId(departmentId);
    }
}
