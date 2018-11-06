package pl.umcs.repository;

import org.springframework.data.repository.CrudRepository;
import pl.umcs.model.AdultsPeople;

public interface AdultsPeopleRepository extends CrudRepository<AdultsPeople, Long> {

}
