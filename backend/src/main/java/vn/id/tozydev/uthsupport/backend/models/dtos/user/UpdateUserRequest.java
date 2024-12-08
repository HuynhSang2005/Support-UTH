package vn.id.tozydev.uthsupport.backend.models.dtos.user;

import lombok.Data;
import vn.id.tozydev.uthsupport.backend.models.enums.UserRole;

@Data
public class UpdateUserRequest {
  private String username;
  private String email;
  private String fullName;
  private UserRole role;
}
