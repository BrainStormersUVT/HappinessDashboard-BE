package brainstormers.ibm.happinesdashbord.service;

import brainstormers.ibm.happinesdashbord.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }
}
