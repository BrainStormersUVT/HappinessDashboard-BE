package brainstormers.ibm.happinesdashbord.service;

import brainstormers.ibm.happinesdashbord.model.Vote;
import brainstormers.ibm.happinesdashbord.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote addVote(Vote vote) {return voteRepository.save(vote);}

    public Vote updateVote(Vote vote) {return voteRepository.save(vote);}

    public Collection<Vote> findVotesByPollId(Long poolId)
    {
        return voteRepository.findVoteByPollId(poolId);
    }

    public void deleteVoteById(Long id) {voteRepository.deteleVoteById(id);}
}
