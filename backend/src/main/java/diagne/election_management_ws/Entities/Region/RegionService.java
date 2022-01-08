package diagne.election_management_ws.Entities.Region;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService
{
    private final RegionRepository regionRepo;

    public RegionService(RegionRepository regionRepository)
    {
        this.regionRepo = regionRepository;
    }


    public List<Region> getAllRegions()
    {
        return this.regionRepo.findAll();
    }
}
