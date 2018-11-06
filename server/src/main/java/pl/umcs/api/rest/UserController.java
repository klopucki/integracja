package pl.umcs.api.rest;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.common.User;
import pl.umcs.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        Iterable<User> usersIterable = userRepository.findAll();
        List<User> users = Lists.newArrayList(usersIterable);
        log.info("Retrieve objects {}", users);
        return users;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public User save(@RequestBody User user) {
        User savedUser = userRepository.save(user);

        log.info("Add user {}", savedUser);
        return savedUser;
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            log.info("Find the only user {}", user);
            return user.get();
        }
        log.error("Cannot find user with id {}", id);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        log.info("Delete user with id {}", id);
        return new ResponseEntity(NO_CONTENT);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user) {
        User updatedUser = userRepository.save(user);
        log.info("Updated User {}", updatedUser);
        return updatedUser;
    }

    /**
     * we don't need this because of user repository and save method
     * @param oldUser
     * @param user
     */
    @Deprecated
    private void updateUser(User oldUser, User user) {
        oldUser.setAge(user.getAge());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setGender(user.getGender());
    }
}
