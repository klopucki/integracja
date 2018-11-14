package pl.umcs.services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import pl.umcs.common.User;
import pl.umcs.events.model.EventObject;
import pl.umcs.events.model.EventType;
import pl.umcs.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import static pl.umcs.events.model.EventType.*;

@Service
public class UserService {

    public static final String EVENT_LISTENER_DESTINATION = "events";
    private final UserRepository userRepository;

    private final JmsTemplate jmsTemplate;

    @Autowired
    public UserService(UserRepository userRepository, JmsTemplate jmsTemplate) {
        this.userRepository = userRepository;
        this.jmsTemplate = jmsTemplate;
    }

    public User save(User user) {
        User savedUser = userRepository.save(user);

        runEvent(CREATE, savedUser.getId(), "Successfull User Saved");

        return savedUser;
    }

    public List<User> findAll() {
        Iterable<User> usersIterable = userRepository.findAll();
        List<User> users = Lists.newArrayList(usersIterable);

        return users;
    }

    public User find(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public User update(User user) {
        User updatedUser = userRepository.save(user);

        runEvent(UPDATE, updatedUser.getId(), "Successfull User Update");

        return updatedUser;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);

        runEvent(DELETE, id, "Successfull User Delete");
    }

    private void runEvent(EventType create, Long id, String message) {
        EventObject eventObject = new EventObject();
        eventObject.setEventType(create);
        eventObject.setObjectId(id);
        eventObject.setMessage(message);
        jmsTemplate.convertAndSend(EVENT_LISTENER_DESTINATION, eventObject);
    }
}
