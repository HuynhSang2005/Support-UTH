package vn.id.tozydev.uthsupport.backend.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import vn.id.tozydev.uthsupport.backend.models.dtos.auth.RegisterForm;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.CreateUserRequest;
import vn.id.tozydev.uthsupport.backend.models.enums.UserRole;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = UserRole.class)
public interface AuthMapper {
  @Mapping(
      target = "role",
      expression = "java(form.isLecture() ? UserRole.LECTURE : UserRole.STUDENT)")
  CreateUserRequest toCreateUserRequest(RegisterForm form);
}
