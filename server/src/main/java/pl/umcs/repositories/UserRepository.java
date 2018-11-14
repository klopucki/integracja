package pl.umcs.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.umcs.common.User;

public interface UserRepository extends CrudRepository<User, Long> {


}
