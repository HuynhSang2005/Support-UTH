package vn.id.tozydev.uthsupport.backend.services;

import vn.id.tozydev.uthsupport.backend.models.dtos.auth.LoginForm;
import vn.id.tozydev.uthsupport.backend.models.dtos.auth.RegisterForm;
import vn.id.tozydev.uthsupport.backend.models.dtos.auth.TokenResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;

public interface AuthService {
  UserResponse register(RegisterForm form);

  TokenResponse login(LoginForm form);
}
