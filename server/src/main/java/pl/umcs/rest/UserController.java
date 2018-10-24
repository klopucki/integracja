package pl.umcs.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.common.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private static List<User> users = new ArrayList<>();

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        log.info("Retrieve objects {}", users);
        return users;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public User save(@RequestBody User user) {
        user.setId(users.size() + 1);
        users.add(user);

        log.info("Add user {}", user);
        return user;
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
        User user = getUser(id);
        log.info("Find the only user {}", user);
        return user;
    }

    @DeleteMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@RequestBody User user) {
        removeUser(user.getId());
        log.info("Delete user with id {}", user.getId());
        return new ResponseEntity(NO_CONTENT);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user) {
        User oldUser = getUser(user.getId());
        log.info("Updating - the old object {}", oldUser);
        updateUser(oldUser, user);
        log.info("And new user {}", user);
        return user;
    }

    private void updateUser(User oldUser, User user) {
        oldUser.setAge(user.getAge());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setGender(user.getGender());
    }

    private void removeUser(Integer id) {
        User foundUser = getUser(id);
        users.remove(foundUser);
    }

    private User getUser(Integer id) {
        Optional<User> user = users.stream().filter(it -> it.getId() == id).findFirst();
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }
}
