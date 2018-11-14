package pl.umcs.events;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import pl.umcs.common.Event;
import pl.umcs.events.model.EventObject;
import pl.umcs.events.model.EventType;
import pl.umcs.repositories.EventRepository;

import java.util.Date;
import java.util.Map;

@Component
public class EventReceiver {

    private static final Map<EventType, String> tittles = ImmutableMap.of(
            EventType.CREATE, "User with id %d was created",
            EventType.UPDATE, "User with id %d was updated",
            EventType.DELETE, "User with id %d was deleted"
    );

    private final EventRepository eventRepository;

    @Autowired
    public EventReceiver(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @JmsListener(destination = "events", containerFactory = "myFactory")
    public void receiveMessage(EventObject eventObject) {
        Event event = prepareMessage(eventObject);

        eventRepository.save(event);
    }

    private Event prepareMessage(EventObject eventObject) {
        Event event = new Event();
        event.setTitle(String.format(
                tittles.get(eventObject.getEventType()),
                eventObject.getObjectId())
        );
        event.setMessage(eventObject.getMessage());
        event.setReaded(false);
        event.setEventDate(new Date());
        return event;
    }
}
