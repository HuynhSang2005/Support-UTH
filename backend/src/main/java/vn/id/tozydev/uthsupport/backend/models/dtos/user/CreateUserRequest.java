package vn.id.tozydev.uthsupport.backend.models.dtos.user;

import lombok.Data;
import vn.id.tozydev.uthsupport.backend.models.enums.UserRole;

@Data
public class CreateUserRequest {
  private String username;
  private String password;
  private String email;
  private String fullName;
  private UserRole role;
}
