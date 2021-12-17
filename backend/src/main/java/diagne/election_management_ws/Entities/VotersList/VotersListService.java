package diagne.election_management_ws.Entities.VotersList;

import diagne.election_management_ws.Entities.Elector.Elector;
import diagne.election_management_ws.Entities.VoteOffice.VoteOfficeRepository;
import diagne.election_management_ws.Entities.VoteOffice.VoteOfficeService;
import diagne.election_management_ws.Model.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VotersListService
{
    private final VotersListRepository votersListRepo;

    @Autowired
    public VotersListService(VotersListRepository votersListRepository)
    {
        this.votersListRepo = votersListRepository;
    }

    public VotersList checkSubscription(Elector elector)
    {
        List<Elector> toFind = new ArrayList<>();
        toFind.add(elector);
        Optional<VotersList> votersListsFound = this.votersListRepo.getVotersListByElectors(toFind);
        if(votersListsFound.isEmpty())
            throw new EntityNotFoundException("Cet electeur n'est inscrit sur aucune liste électorale");
        return votersListsFound.get();
    }

    public void subscribe(Elector elector)
    {

    }
}
