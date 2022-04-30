package brainstormers.ibm.happinesdashbord.service;

import brainstormers.ibm.happinesdashbord.exception.UserNotFoundExcepiton;
import brainstormers.ibm.happinesdashbord.model.User;
import brainstormers.ibm.happinesdashbord.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deleteUserById(Long id)
    {
        userRepository.deleteUserById(id);
    }
}
