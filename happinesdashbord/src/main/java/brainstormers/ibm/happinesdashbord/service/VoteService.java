package brainstormers.ibm.happinesdashbord.service;

import brainstormers.ibm.happinesdashbord.model.Vote;
import brainstormers.ibm.happinesdashbord.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class VoteService {
    private final VoteRepository voteRepository;


    public Vote addVote(Vote vote) {return voteRepository.save(vote);}

    public Vote updateVote(Vote vote) {return voteRepository.save(vote);}

    public Collection<Vote> findVotesByPollId(Long poolId)
    {
        return voteRepository.findVoteByPollId(poolId);
    }

    public Collection<Long> getPoolVotesWithingGivenTime(Long poolId, Date startDate, Date endDate){
        return voteRepository.getPoolVotesWithingGivenTime(poolId, startDate, endDate);
    }

    public Collection<Long> getListOfGrades(Long poolId)
    {
        Collection<Vote> votes = voteRepository.findVoteByPollId(poolId);

        HashMap<Integer, Long> mapedGrades= new HashMap<Integer, Long>();
        for(int i = 1; i <= 10; i++)
            mapedGrades.put(i, 0l);
        for(Vote vote : votes)
        {
            int grade = vote.getGrade();
            long newVal =  mapedGrades.get(grade) + 1;
            mapedGrades.put(grade, newVal);
        }

        Collection<Long> rez = new Vector<Long>();
        for(int i = 1; i <= 10; i++)
        {
            rez.add(mapedGrades.get(i));
        }
        return rez;
    }

    public void deleteVoteById(Long id) {voteRepository.deleteVoteById(id);}
}
