package brainstormers.ibm.happinesdashbord.controler;

import brainstormers.ibm.happinesdashbord.model.User;
import brainstormers.ibm.happinesdashbord.model.Vote;
import brainstormers.ibm.happinesdashbord.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/vote")
public class VoteResource {
    public final VoteService voteService;

    public VoteResource(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Vote> addVote(@RequestBody Vote vote) {
        Vote newVote = voteService.addVote(vote);
        return new ResponseEntity<Vote>(newVote, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Vote> updateVote(@RequestBody Vote vote) {
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

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteVoteById(@PathVariable("id")Long id) {
        voteService.deleteVoteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
