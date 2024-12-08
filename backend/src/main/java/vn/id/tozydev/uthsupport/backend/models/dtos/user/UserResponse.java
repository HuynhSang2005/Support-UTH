package vn.id.tozydev.uthsupport.backend.models.dtos.user;

import lombok.Data;

@Data
public class UserResponse {
  private Long id;
  private String username;
  private String email;
  private String fullName;
  private String role;
}
