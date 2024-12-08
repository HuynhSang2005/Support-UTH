package vn.id.tozydev.uthsupport.backend.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import vn.id.tozydev.uthsupport.backend.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByUsername(String username);

  void deleteUserByUsername(String username);
}
