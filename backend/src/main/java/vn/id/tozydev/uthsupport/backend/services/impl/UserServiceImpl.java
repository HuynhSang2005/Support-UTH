package vn.id.tozydev.uthsupport.backend.services.impl;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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

    var user = userOpt.get();
    userMapper.update(request, user);
    userRepository.save(user);
    return Optional.of(userMapper.toResponse(user));
  }

  @Override
  public void delete(String username) {
    userRepository.deleteUserByUsername(username);
  }
}
