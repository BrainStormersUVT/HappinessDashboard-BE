package brainstormers.ibm.happinesdashbord.service;

import brainstormers.ibm.happinesdashbord.exception.PollNotFoundException;
import brainstormers.ibm.happinesdashbord.model.Poll;
import brainstormers.ibm.happinesdashbord.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PollService {
    private final PollRepository pollRepository;

    private static Poll pollDummy;

    static
    {
        pollDummy = new Poll();
        pollDummy.setId(-1l);
    }
    public Poll addPoll(Poll poll) { return pollRepository.save(poll);}

    public Poll updatePoll(Poll poll) {return pollRepository.save(poll);};

    public Poll findPollById(Long id)
    {
        Optional<Poll> rez =  pollRepository.findPollById(id);
        if(rez.isPresent())
            return rez.get();
        else
            return pollDummy;
    }

    public Collection<Poll> getListOfPools()
    {
        return pollRepository.findAllPools();
    }

    public Collection<Poll> getListOfLatestPools()
    {
        ArrayList<Poll> poolList = new ArrayList<Poll>(pollRepository.findAllPools());

        return poolList.subList(0, 3);
    }

    public Collection<Poll> getPollsByUsername(String username)
    {
        ArrayList<Poll> poolList = new ArrayList<Poll>(pollRepository.findPoolsByUsername(username));

        return poolList;
    }

    public Collection<Poll> getPollsByTitle(String title)
    {
        ArrayList<Poll> poolList = new ArrayList<Poll>(pollRepository.getPollsByTitle(title));

        return poolList;
    }
    public void deletePollById(Long id) {pollRepository.deletePollById(id);};

}
