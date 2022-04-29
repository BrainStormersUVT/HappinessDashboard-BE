package brainstormers.ibm.happinesdashbord.service;

import brainstormers.ibm.happinesdashbord.repository.PollRepository;
import org.springframework.stereotype.Service;

@Service
public class PollService {
    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }
}
