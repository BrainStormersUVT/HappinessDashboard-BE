package brainstormers.ibm.happinesdashbord.repository;

import brainstormers.ibm.happinesdashbord.model.User;
import brainstormers.ibm.happinesdashbord.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Collection<Vote> findVoteByPollId(Long id);
}
