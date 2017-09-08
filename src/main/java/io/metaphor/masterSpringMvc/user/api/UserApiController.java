package io.metaphor.masterSpringMvc.user.api;

import io.metaphor.masterSpringMvc.user.User;
import io.metaphor.masterSpringMvc.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApiController {
    private UserRepository userRepository;

    @Autowired
    public UserApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        HttpStatus status = HttpStatus.OK;
        if(!userRepository.exists(user.getEmail()))
        {
            status=HttpStatus.CREATED;
        }
        User saved= userRepository.save(user);
        return new ResponseEntity(saved,status);
    }
    @RequestMapping(value = "/users/{email}",method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User user) {
        if(!userRepository.exists(user.getEmail()))
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        User saved= userRepository.save(email,user);
        return new ResponseEntity(saved,HttpStatus.CREATED);

    }
    @RequestMapping(value = "/users/{email}",method = RequestMethod.DELETE)
    public ResponseEntity<User>  deleteUser(@PathVariable String email) {
        if(!userRepository.exists(email))
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
         userRepository.delete(email);
        return new ResponseEntity(HttpStatus.OK);
    }
}
