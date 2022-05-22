package brainstormers.ibm.happinesdashbord.service;

import brainstormers.ibm.happinesdashbord.exception.UserNotFoundExcepiton;
import brainstormers.ibm.happinesdashbord.model.User;
import brainstormers.ibm.happinesdashbord.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User addUser(User user) {return userRepository.save(user);}

    public User updateUser(User user)
    {
        return userRepository.save(user);
    }

    public Long findUserById(Long id)
    {
        if(userRepository.findUserById(id).isPresent())
            return id;
        else
            return -1L;
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
}
