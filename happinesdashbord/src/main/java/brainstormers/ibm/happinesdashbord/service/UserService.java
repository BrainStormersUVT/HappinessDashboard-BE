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


    public User addUser(User user) {return userRepository.save(user);}

    public User updateUser(User user)
    {
        return userRepository.save(user);
    }

    public User findUserById(Long id)
    {
       return userRepository.findUserById(id)
               .orElseThrow(() ->
                    new UserNotFoundExcepiton
                       ("User with id: " + id + " was not found."));
    }

    public User findUserByUsername(String username)
    {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundExcepiton
                                ("User with username: " + username + " was not found."));
    }

    public String findPasswordByUsername(String username)
    {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundExcepiton
                                ("User with username: " + username + " was not found."));
        return user.getPassword();

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

    public Boolean checkIfUserExists(String password, String username) {
        Optional<User> user= userRepository.checkIfUserExists(password, username);
        if(user.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }
}
