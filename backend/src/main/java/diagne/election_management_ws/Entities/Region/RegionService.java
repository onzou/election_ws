package diagne.election_management_ws.Entities.Region;

import org.springframework.stereotype.Service;

@Service
public class RegionService
{
    private final RegionRepository regionRepo;

    public RegionService(RegionRepository regionRepository)
    {
        this.regionRepo = regionRepository;
    }


}
