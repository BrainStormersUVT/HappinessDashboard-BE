package brainstormers.ibm.happinesdashbord.controler;

import brainstormers.ibm.happinesdashbord.exception.PollNotFoundException;
import brainstormers.ibm.happinesdashbord.model.Poll;
import brainstormers.ibm.happinesdashbord.service.PollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/poll")
public class PollResource {
    private final PollService pollService;

    public PollResource(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping("/add")
    public ResponseEntity<Poll> addPoll(@RequestBody Poll poll) {
        Poll newPoll= pollService.addPoll(poll);
        return new ResponseEntity<Poll>(newPoll, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Poll> updatePoll(@RequestBody Poll poll) {
        Poll updatePoll = pollService.updatePoll(poll);
        return new ResponseEntity<Poll>(updatePoll, HttpStatus.OK);
    };

    @GetMapping("/find/{id}")
    public ResponseEntity<Poll> findPollById(@PathVariable("id") Long id)
    {
        Poll poll = pollService.findPollById(id);
        return new ResponseEntity<Poll>(poll, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Collection<Poll>> getListOfPools()
    {
        Collection<Poll> pollCollection = pollService.getListOfPools();
        return new ResponseEntity<Collection<Poll>>(pollCollection, HttpStatus.OK);
    }

    @GetMapping("/findLatest")
    public ResponseEntity<Collection<Poll>> getListOfLatestPools()
    {
        Collection<Poll> pollCollection = pollService.getListOfLatestPools();
        return new ResponseEntity<Collection<Poll>>(pollCollection, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePollById(@PathVariable("id") Long id) {
        pollService.deletePollById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
