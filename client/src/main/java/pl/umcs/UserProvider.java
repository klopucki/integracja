package pl.umcs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.umcs.common.User;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static pl.umcs.common.Gender.MALE;

public class UserProvider {

    private static final Logger log = LoggerFactory.getLogger(UserProvider.class);

    public static final String USERS_URL = "http://localhost:8080/users";
    private RestTemplate restTemplate = new RestTemplate();

    public void addUser() {
        User user = createUserMethod();

        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<User> response = restTemplate
                .exchange(USERS_URL, POST, request, User.class);

        User responseBody = response.getBody();

        log.info("I have created user {}", responseBody);
    }

    public void printUserAsJson() {
        ResponseEntity<String> response
                = restTemplate.getForEntity(USERS_URL + "/1", String.class);

        log.info(response.getBody());
    }

    public void printUserAsObject() {
        User response = restTemplate.getForObject(USERS_URL + "/1", User.class);

        log.info("Fetched user {}", response);
    }

    public void fetchAllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(
                USERS_URL,
                GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = response.getBody();
        log.info("Fetched all users {}", users);
    }

    public void updateUser() {
        User updatedInstance = new User(1, "Darek", "Wos", 44, MALE);

        HttpEntity<User> requestUpdate = new HttpEntity<>(updatedInstance);
        restTemplate.exchange(USERS_URL, HttpMethod.PUT, requestUpdate, Void.class);
    }

    public void deleteUser() {
        String entityUrl = USERS_URL + "/2";
        restTemplate.delete(entityUrl);
    }

    private User createUserMethod() {
        return new User("Mariusz", "Kowalski", 34, MALE);
    }
}
