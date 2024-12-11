package vn.id.tozydev.uthsupport.backend.services;

import java.util.Optional;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.CreateUserRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UpdateUserRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;

public interface UserService {
  Iterable<UserResponse> findAll();

  Optional<UserResponse> findOne(String username);

  UserResponse create(CreateUserRequest request);

  Optional<UserResponse> update(String username, UpdateUserRequest request);

  void delete(String username);
}
