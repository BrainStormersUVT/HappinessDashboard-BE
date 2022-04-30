package brainstormers.ibm.happinesdashbord.repository;

import brainstormers.ibm.happinesdashbord.model.User;
import brainstormers.ibm.happinesdashbord.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query(
            value = "SELECT * FROM Vote WHERE poll_id = :id ORDER BY datetime DESC",
            nativeQuery = true)
    Collection<Vote> findVoteByPollId(@Param("id") Long id);

    void deteleVoteById(Long id);
}
