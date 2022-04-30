package brainstormers.ibm.happinesdashbord.repository;

import brainstormers.ibm.happinesdashbord.model.Poll;
import brainstormers.ibm.happinesdashbord.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

    @Query(
            value = "SELECT * FROM Poll ORDER BY date DESC",
            nativeQuery = true)
    Collection<Poll> findAllPools();

    Optional<Poll> findPollById(Long id);

    void deletePollById(Long id);
}
