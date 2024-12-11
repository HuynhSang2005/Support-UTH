package vn.id.tozydev.uthsupport.backend.models.dtos.user;

import jakarta.validation.constraints.*;
import lombok.Data;
import vn.id.tozydev.uthsupport.backend.models.enums.UserRole;

@Data
public class UpdateUserRequest {
  @Size(min = 3, max = 32)
  @Pattern(regexp = "^\\D[\\w_]{2,32}")
  private String username;

  @Email private String email;

  @Size(min = 3, max = 64)
  private String fullName;

  private UserRole role;
}
