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

    public Collection<Vote> findVotesByPollId(Long id)
    {
        return voteRepository.findVoteByPollId(id);
    }
}
