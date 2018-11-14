package pl.umcs.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.common.User;
import pl.umcs.services.UserService;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        List<User> users = userService.findAll();

        log.info("Retrieve objects {}", users);

        return users;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public User save(@RequestBody User user) {
        User savedUser = userService.save(user);

        log.info("Add user {}", savedUser);

        return savedUser;
    }

    @GetMapping("/{id}")
    public User find(@PathVariable Long id) {
        return userService.find(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        log.info("Delete user with id {}", id);

        return new ResponseEntity(NO_CONTENT);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user) {
        User updatedUser = userService.update(user);

        log.info("Updated User {}", updatedUser);

        return updatedUser;
    }

    /**
     * we don't need this because of user repository and save method
     *
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
