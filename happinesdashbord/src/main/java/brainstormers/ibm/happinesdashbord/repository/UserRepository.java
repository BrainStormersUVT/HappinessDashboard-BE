package brainstormers.ibm.happinesdashbord.repository;

import brainstormers.ibm.happinesdashbord.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    void deleteUserById(Long id);

    Optional<User> findUserById(Long id);

    Optional<User> findUserByUsername(String username);

    @Query(
            value = "SELECT * FROM User u LEFT JOIN Vote v ON u.id = v.user_id " +
                    "WHERE u.username = :username AND v.poll_id = :idPoll",
            nativeQuery = true)
    Optional<User> checkIfUserVoted(@Param("idPoll")Integer idPoll,@Param("username") String username);

    @Query(
            value = "SELECT * FROM User u WHERE u.username=:username AND u.password=:password",
            nativeQuery = true)
    Optional<User> checkIfUserExists(String password, String username);

    @Query(value = "SELECT * FROM User u LEFT JOIN Poll p on u.id=p.creator_id WHERE u.username=:username AND  p.id=:idPoll",
            nativeQuery = true)
    Optional<User> checkIfUserIsCreator(@Param("idPoll") Integer idPoll, @Param("username") String username);
}
