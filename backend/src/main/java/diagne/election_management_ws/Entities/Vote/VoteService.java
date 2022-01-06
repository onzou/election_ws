package diagne.election_management_ws.Entities.Vote;

import diagne.election_management_ws.Entities.Arrondissement.Arrondissement;
import diagne.election_management_ws.Entities.Elector.Elector;
import diagne.election_management_ws.Entities.Elector.ElectorException;
import diagne.election_management_ws.Entities.Elector.ElectorService;
import diagne.election_management_ws.Entities.VoteOffice.VoteOffice;
import diagne.election_management_ws.Entities.VotersList.VotersListService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
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
            VoteOffice voteOffice = voteOfficeOptional.get();
            //vérifier si candidat existe déjà dans les votes.
            if(candidate != null)
            {
                Optional<Vote> voteOptional = this.voteRepo.getVoteByCandidate_IdAndVoteOffice_Id(candidate.getId(),voteOffice.getId());
                // si oui, rechercher la voie correspondant à ce candidat et incrémenter son nombre de voies

                if(voteOptional.isPresent())
                {
                    Vote voteAlreadySaved = voteOptional.get();
                    voteAlreadySaved.setVoteNumber(voteAlreadySaved.getVoteNumber()+1);
                    this.voteRepo.save(voteAlreadySaved);
                }
            }
            //sinon, Enregistrer une toute nouvelle voie.
            else
            {
                Vote voteToSave = new Vote();
                voteToSave.setCandidate(candidate);
                voteToSave.setVoter(elector);
                voteToSave.setVoteOffice(voteOffice);
                this.voteRepo.save(voteToSave);
            }
            this.electorService.updateAfterVoting(elector);
        }
        else
            throw new ElectorException("Erreur lors du vote!");
        //lever une exception si l'électeur a déjà voté. l'exception doit être levée automatically
    }
}
