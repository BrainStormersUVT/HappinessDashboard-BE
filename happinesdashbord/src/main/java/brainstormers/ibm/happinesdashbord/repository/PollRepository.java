package brainstormers.ibm.happinesdashbord.repository;

import brainstormers.ibm.happinesdashbord.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

    @Query(
            value = "SELECT  * FROM Poll ORDER BY date DESC",
            nativeQuery = true)
    Collection<Poll> findAllPools();

    Optional<Poll> findPollById(Long id);

    void deletePollById(Long id);

    @Query(
            value = "SELECT * FROM Poll p LEFT JOIN User u "+
                    "ON p.creator_id = u.id " +
                    "WHERE u.username = :username ORDER BY date DESC",
            nativeQuery = true)
    Collection<Poll> findPoolsByUsername(@Param("username")String username);

    @Query(
            value = "SELECT * FROM Poll p WHERE p.title LIKE %:title%",
            nativeQuery = true)
    Collection<Poll> getPollsByTitle(@Param("title")String title);
}
