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
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/poll")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PollResource {
    private final PollService pollService;
    private final UserService userService;
    public final VoteService voteService;

    @PostMapping("/add/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Long> addPoll(@RequestBody Poll poll, @PathVariable("userId") Long userId) {
        User user = userService.findUserById(userId);
        poll.setCreator(user);
        Poll newPoll = pollService.addPoll(poll);
        if(user.getId() == -1l)
        {
            newPoll.setId(-1l);
        }

        return new ResponseEntity<>(newPoll.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Poll> updatePoll(@RequestBody Poll poll, @PathVariable("userId") Long userId) {
        Poll updatePoll = pollService.updatePoll(poll);

        return new ResponseEntity<Poll>(updatePoll, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Poll> findPollById(@PathVariable("id") Long id)
    {
        Poll poll = pollService.findPollById(id);
        return new ResponseEntity<Poll>(poll, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Collection<Poll>> getListOfPools()
    {
        Collection<Poll> pollCollection = pollService.getListOfPools();
        return new ResponseEntity<Collection<Poll>>(pollCollection, HttpStatus.OK);
    }

    @GetMapping("/findLatest")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Collection<Poll>> getListOfLatestPools()
    {
        Collection<Poll> pollCollection = pollService.getListOfLatestPools();
        return new ResponseEntity<Collection<Poll>>(pollCollection, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deletePollById(@PathVariable("id") Long id) {
        pollService.deletePollById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getPolls/{username}")
    @CrossOrigin(origins = "http://localhost:4200")
    public  ResponseEntity<Collection<Poll>> getPollsByUsername(@PathVariable("username")String username)
    {
        Collection<Poll> pollCollection = pollService.getPollsByUsername(username);
        return new ResponseEntity<Collection<Poll>>(pollCollection, HttpStatus.OK);
    }

    @GetMapping("/getPollsWithTitleLike/{title}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Collection<Poll>> getPollsByTitle(@PathVariable("title")String title)
    {
        Collection<Poll> pollCollection = pollService.getPollsByTitle(title);
        return new ResponseEntity<Collection<Poll>>(pollCollection, HttpStatus.OK);
    }

    @GetMapping("/exportToCSV/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void exportToCSV(@PathVariable("id") Long id, HttpServletResponse response)
    {
        response.setContentType("text/csv");
        Poll pollToBeExported = pollService.findPollById(id);

        if(pollToBeExported.getId() != -1)
        {
            String poolTitle = pollToBeExported.getTitle();
            String fileName = poolTitle + ".csv";

            String headerKey = "Content-Disposition";
            String headerValue = "attachement; filename=" + fileName;

            response.setHeader(headerKey, headerValue);

            Collection<Vote> votes = voteService.findVotesByPollId(id);

            try {
                ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

                String[] csvHeader = {"Grade", "Comment", "Date"};
                String[] nameMapping = {"grade", "comment", "datetime"};

                csvBeanWriter.writeHeader(csvHeader);

                for(Vote vote : votes)
                {
                    csvBeanWriter.write(vote, nameMapping);
                }

                csvBeanWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
