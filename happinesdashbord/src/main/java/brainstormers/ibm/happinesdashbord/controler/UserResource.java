package brainstormers.ibm.happinesdashbord.controler;

import brainstormers.ibm.happinesdashbord.model.User;
import brainstormers.ibm.happinesdashbord.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserResource {
    private final UserService userService;


    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        User newUser = userService.addUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> updateUser(@RequestBody User user)
    {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id)
    {
        User userId = userService.findUserById(id);
        return new ResponseEntity<User>(userId, HttpStatus.OK);
    }

    @GetMapping("/find/{username}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> findUserByUsername(@PathVariable("username") String username)
    {
        User user =   userService.findUserByUsername(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/find/{username}/password")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> findPasswordByUsername(@PathVariable("username") String username)
    {
        String password = userService.findPasswordByUsername(username);
        return new ResponseEntity<String>(password, HttpStatus.OK);
    }

    @GetMapping("/checkIfUserVoted/{idPoll}/{username}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Boolean> checkIfUserVoted(@PathVariable("idPoll")Integer idPoll,@PathVariable("username") String username)
    {
        Boolean voted = userService.checkIfUserVoted(idPoll, username);
        return new ResponseEntity<Boolean>(voted, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id)
    {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
