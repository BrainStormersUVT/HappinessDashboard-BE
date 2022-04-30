package brainstormers.ibm.happinesdashbord.repository;

import brainstormers.ibm.happinesdashbord.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query(
            value = "SELECT * FROM Vote WHERE poll_id = :poolId ORDER BY datetime DESC",
            nativeQuery = true)
    Collection<Vote> findVoteByPollId(@Param("poolId") Long poolId);

    void deleteVoteById(Long id);
}
