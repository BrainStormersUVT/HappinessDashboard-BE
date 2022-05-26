package brainstormers.ibm.happinesdashbord.service;

import brainstormers.ibm.happinesdashbord.exception.UserNotFoundExcepiton;
import brainstormers.ibm.happinesdashbord.model.User;
import brainstormers.ibm.happinesdashbord.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService {
    private final UserRepository userRepository;

    private static User userDummy;

    static
    {
        userDummy = new User();
        userDummy.setId(-1l);
    }

    public User addUser(User user) {return userRepository.save(user);}

    public User updateUser(User user)
    {
        return userRepository.save(user);
    }

    public User findUserById(Long id)
    {
        Optional<User> rez = userRepository.findUserById(id);

        if(rez.isPresent())
            return rez.get();
        else
            return userDummy;
    }

    public User findUserByUsername(String username)
    {
        Optional<User> rez = userRepository.findUserByUsername(username);

        if(rez.isPresent())
            return rez.get();
        else
            return userDummy;
    }

    public Boolean checkIfUserVoted(Integer idPoll, String username)
    {
        Optional<User> user = userRepository.checkIfUserVoted(idPoll, username);

        if(user.isPresent())
            return true;
        else
            return false;
    }
    public void deleteUserById(Long id)
    {
        userRepository.deleteUserById(id);
    }

    public User checkIfUserExists(String password, String username) {
        Optional<User> user= userRepository.checkIfUserExists(password, username);
        if(user.isPresent()){
            return findUserByUsername(username);
        }
        else {
            return null;
        }
    }

    public Boolean checkIfUserIsCreator(Integer idPoll, String username) {
        Optional<User> user = userRepository.checkIfUserIsCreator(idPoll, username);

        if(user.isPresent())
            return true;
        else
            return false;

    }
}
