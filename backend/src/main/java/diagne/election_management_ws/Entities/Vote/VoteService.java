package diagne.election_management_ws.Entities.Vote;

import diagne.election_management_ws.Entities.Elector.Elector;
import diagne.election_management_ws.Entities.Elector.ElectorException;
import diagne.election_management_ws.Entities.Elector.ElectorService;
import diagne.election_management_ws.Entities.VoteOffice.VoteOffice;
import diagne.election_management_ws.Entities.VotersList.VotersListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class VoteService
{
    private final VoteRepository voteRepo;
    private final ElectorService electorService;
    private final VotersListService votersListService;

    public VoteService(VoteRepository voteRepo,
                       ElectorService electorService,
                       VotersListService votersListService)
    {
        this.voteRepo = voteRepo;
        this.electorService = electorService;
        this.votersListService = votersListService;
    }

    public void vote(Elector elector, Elector candidate)
    {
        //avoir les votes du bureau de vote tel et de tel arrondissement;
        Optional<VoteOffice> voteOfficeOptional = votersListService.getVoteOfficeByNameAndArrondissement(elector.getVoteOffice(),elector.getArrondissement());
        if(voteOfficeOptional.isPresent())
        {
            //vérifier si candidat existe déjà dans les votes.
            Vote voteToSave = new Vote();
            voteToSave.setCandidate(candidate);
            voteToSave.setVoterId(elector.getId());
            voteToSave.setVoteOffice(voteOfficeOptional.get());
            this.voteRepo.save(voteToSave);
            this.electorService.updateAfterVoting(elector);
        }
        else
            throw new ElectorException("Erreur lors du vote!");
        //lever une exception si l'électeur a déjà voté. l'exception doit être levée automatically
    }

    public List<Vote> getVotesByVoteOffice(Long id)
    {
        return this.voteRepo.getVotesByVoteOfficeId(id);
    }

    public int getBulletinNulByVoteOffice(Long id)
    {
        return this.voteRepo.getBulletinNulByVoteOffice(id);
    }
    public int getNumberOfVotesByCandidate(Long candidateId)
    {
        return this.voteRepo.getNumberOfVotesByCandidateId(candidateId);
    }

    public int getNumberOfVotesByCandidateInArrondissement(Long candidateId, Long arrondissementId)
    {
        return this.voteRepo.getNumberOfVotesByCandidateIdAndArrondissementId(candidateId,arrondissementId);
    }

//    public int getVotesByCandidate(Long vote)
}
