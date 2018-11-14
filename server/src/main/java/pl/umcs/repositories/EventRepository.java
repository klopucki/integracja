package pl.umcs.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.umcs.common.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
}
