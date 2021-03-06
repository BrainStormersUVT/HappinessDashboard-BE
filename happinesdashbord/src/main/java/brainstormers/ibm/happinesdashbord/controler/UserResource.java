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

    @GetMapping("/checkIfUserVoted/{idPoll}/{username}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Boolean> checkIfUserVoted(@PathVariable("idPoll")Integer idPoll,@PathVariable("username") String username)
    {
        Boolean voted = userService.checkIfUserVoted(idPoll, username);
        return new ResponseEntity<Boolean>(voted, HttpStatus.OK);
    }

    @GetMapping("/checkIfUserIsCreator/{idPoll}/{username}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Boolean> checkIfUserIsCreator(@PathVariable Integer idPoll, @PathVariable String username){
        Boolean created=userService.checkIfUserIsCreator(idPoll, username);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }
    @GetMapping("/checkIfUserExists/{username}/{password}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> checkIfUserExist(@PathVariable String password, @PathVariable String username){
        User user = userService.checkIfUserExists(password, username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id)
    {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
