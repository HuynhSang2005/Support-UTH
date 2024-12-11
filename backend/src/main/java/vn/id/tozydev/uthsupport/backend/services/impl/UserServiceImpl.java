package vn.id.tozydev.uthsupport.backend.services.impl;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.CreateUserRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UpdateUserRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;
import vn.id.tozydev.uthsupport.backend.models.mappers.UserMapper;
import vn.id.tozydev.uthsupport.backend.repositories.UserRepository;
import vn.id.tozydev.uthsupport.backend.services.UserService;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Iterable<UserResponse> findAll() {
    return userMapper.toResponses(userRepository.findAll());
  }

  @Override
  public Optional<UserResponse> findOne(String username) {
    return userRepository.findByUsername(username).map(userMapper::toResponse);
  }

  @Override
  public UserResponse create(CreateUserRequest request) {
    checkUsernameExisted(request.getUsername());
    checkEmailExisted(request.getEmail());
    var user = userMapper.toEntity(request);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return userMapper.toResponse(user);
  }

  @Override
  public Optional<UserResponse> update(String username, UpdateUserRequest request) {
    var userOpt = userRepository.findByUsername(username);
    if (userOpt.isEmpty()) {
      return Optional.empty();
    }

    if (request.getUsername() != null) {
      checkUsernameExisted(request.getUsername());
    }

    if (request.getEmail() != null) {
      checkEmailExisted(request.getEmail());
    }

    var user = userOpt.get();
    userMapper.update(request, user);
    userRepository.save(user);
    return Optional.of(userMapper.toResponse(user));
  }

  private void checkUsernameExisted(String username) {
    if (userRepository.existsByUsername(username)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already existed!");
    }
  }

  private void checkEmailExisted(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already existed!");
    }
  }

  @Override
  public void delete(String username) {
    userRepository.deleteUserByUsername(username);
  }
}
