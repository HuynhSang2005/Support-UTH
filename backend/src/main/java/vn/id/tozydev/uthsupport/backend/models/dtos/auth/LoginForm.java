package vn.id.tozydev.uthsupport.backend.models.dtos.auth;

import lombok.Data;

@Data
public class LoginForm {
  private String username;
  private String password;
}
