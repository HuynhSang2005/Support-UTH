package vn.id.tozydev.uthsupport.backend.models;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.id.tozydev.uthsupport.backend.models.entities.User;
import vn.id.tozydev.uthsupport.backend.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserAuditorAware implements AuditorAware<User> {
  private final UserRepository userRepository;

  @Override
  public @NonNull Optional<User> getCurrentAuditor() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      return Optional.empty();
    }

    return userRepository.findByUsername(authentication.getName());
  }
}
