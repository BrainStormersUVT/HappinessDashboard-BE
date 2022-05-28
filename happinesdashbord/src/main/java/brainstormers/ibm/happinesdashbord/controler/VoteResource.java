package brainstormers.ibm.happinesdashbord.controler;

import brainstormers.ibm.happinesdashbord.model.Poll;
import brainstormers.ibm.happinesdashbord.model.User;
import brainstormers.ibm.happinesdashbord.model.Vote;
import brainstormers.ibm.happinesdashbord.service.PollService;
import brainstormers.ibm.happinesdashbord.service.UserService;
import brainstormers.ibm.happinesdashbord.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class VoteResource {
    public final VoteService voteService;
    private final PollService pollService;
    private final UserService userService;

    @PostMapping("/add/{userId}/{pollId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Vote> addVote(@RequestBody Vote vote,
                                        @PathVariable("pollId") Long poolId, @PathVariable("userId") Long userId) {
        User user = userService.findUserById(userId);
        Poll poll = pollService.findPollById(poolId);
        vote.setUser(user);
        vote.setPoll(poll);
        Vote newVote = voteService.addVote(vote);
        if(user.getId() == -1l || poll.getId() == -1l)
        {
            newVote.setId(-1l);
        }
        return new ResponseEntity<Vote>(newVote, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}/{pollId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Vote> updateVote(@RequestBody Vote vote,
                                           @PathVariable("pollId") Long poolId, @PathVariable("userId") Long userId) {
        Vote updatedVote = voteService.updateVote(vote);
        return new ResponseEntity<Vote>(updatedVote, HttpStatus.OK);
    }

    @GetMapping("/find/{poolId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public  ResponseEntity<Collection<Vote>> findVotesByPollId(@PathVariable("poolId") Long poolId)
    {
        Collection<Vote> voteCollection = voteService.findVotesByPollId(poolId);
        return new ResponseEntity<Collection<Vote>>(voteCollection, HttpStatus.OK);
    }

    @GetMapping("/find/{pollId}/{startDate}/{endDate}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Collection<Long>> getPoolVotesWithingGivenTime(@PathVariable("pollId")Long poolId,
                                                                         @PathVariable("startDate") Date startDate,
                                                                         @PathVariable("endDate") Date endDate){
        Collection<Long> voteCollection = voteService.getPoolVotesWithingGivenTime(poolId, startDate, endDate);
        return new ResponseEntity<Collection<Long>>(voteCollection, HttpStatus.OK);
    }

    @GetMapping("/{poolId}/getGrades")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Collection<Long>> getListOfGrades(@PathVariable("poolId")Long poolId)
    {
        Collection<Long> gradeList = voteService.getListOfGrades(poolId);
        return new ResponseEntity<Collection<Long>>(gradeList , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteVoteById(@PathVariable("id")Long id) {
        voteService.deleteVoteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
