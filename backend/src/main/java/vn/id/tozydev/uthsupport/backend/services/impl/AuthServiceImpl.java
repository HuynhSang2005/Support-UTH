package vn.id.tozydev.uthsupport.backend.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.id.tozydev.uthsupport.backend.models.dtos.auth.LoginForm;
import vn.id.tozydev.uthsupport.backend.models.dtos.auth.RegisterForm;
import vn.id.tozydev.uthsupport.backend.models.dtos.auth.TokenResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;
import vn.id.tozydev.uthsupport.backend.models.mappers.AuthMapper;
import vn.id.tozydev.uthsupport.backend.repositories.UserRepository;
import vn.id.tozydev.uthsupport.backend.security.TokenService;
import vn.id.tozydev.uthsupport.backend.services.AuthService;
import vn.id.tozydev.uthsupport.backend.services.UserService;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final AuthMapper authMapper;
  private final UserService userService;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  @Override
  public UserResponse register(RegisterForm form) {
    var request = authMapper.toCreateUserRequest(form);
    return userService.create(request);
  }

  @Override
  public TokenResponse login(LoginForm form) {
    var userOpt = userRepository.findByUsername(form.getUsername());
    if (userOpt.isEmpty()) {
      throw new UsernameNotFoundException(
          "User with username'" + form.getUsername() + "' not found!");
    }

    var user = userOpt.get();
    if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
      throw new InsufficientAuthenticationException("Unauthorized");
    }

    return TokenResponse.builder().accessToken(tokenService.create(user.toUserDetails())).build();
  }
}
