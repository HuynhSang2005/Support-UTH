package vn.id.tozydev.uthsupport.backend.models.dtos.auth;

import lombok.Data;

@Data
public class RegisterForm {
  private String username;
  private String password;
  private String email;
  private String fullName;
  private boolean isLecture;
}
