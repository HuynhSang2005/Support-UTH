package vn.id.tozydev.uthsupport.backend.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.id.tozydev.uthsupport.backend.repositories.UserRepository;

@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var userOpt = userRepository.findByUsername(username);
    if (userOpt.isEmpty()) {
      throw new UsernameNotFoundException("User with username '" + username + "' not found!");
    }
    return userOpt.get().toUserDetails();
  }
}
