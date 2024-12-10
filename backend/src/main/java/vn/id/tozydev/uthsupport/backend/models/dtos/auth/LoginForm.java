package vn.id.tozydev.uthsupport.backend.models.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginForm {
  @NotBlank
  @Size(min = 3, max = 16)
  @Pattern(regexp = "^\\D[\\w_]{2,32}")
  private String username;

  @NotBlank
  @Size(min = 8, max = 32)
  @Pattern(
      regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}",
      message =
          "Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters")
  private String password;
}
