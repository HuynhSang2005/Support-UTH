package vn.id.tozydev.uthsupport.backend.models.mappers;

import org.mapstruct.*;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.CreateUserRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UpdateUserRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;
import vn.id.tozydev.uthsupport.backend.models.entities.User;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
  @Mapping(target = "role", source = "role.authority")
  UserResponse toResponse(User entity);

  Iterable<UserResponse> toResponses(Iterable<User> users);

  User toEntity(CreateUserRequest request);

  void update(UpdateUserRequest request, @MappingTarget User entity);
}
