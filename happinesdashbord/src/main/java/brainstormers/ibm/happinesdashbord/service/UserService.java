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
    public void deleteUser(Long id)
    {
        userRepository.deleteUserById(id);
    }
}
