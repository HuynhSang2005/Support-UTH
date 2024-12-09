package vn.id.tozydev.uthsupport.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import vn.id.tozydev.uthsupport.backend.models.dtos.auth.LoginForm;
import vn.id.tozydev.uthsupport.backend.models.dtos.auth.RegisterForm;
import vn.id.tozydev.uthsupport.backend.models.dtos.auth.TokenResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;
import vn.id.tozydev.uthsupport.backend.services.AuthService;

@RestController
@RequestMapping(value = ApiPaths.AUTH)
@AllArgsConstructor
public class AuthController extends BaseController {
  private final AuthService authService;

  @PostMapping(ApiPaths.REGISTER)
  public ResponseEntity<UserResponse> register(
      @RequestBody RegisterForm form, UriComponentsBuilder ucb) {
    var response = authService.register(form);
    var location =
        ucb.pathSegment(ApiPaths.USERS, ApiPaths.USERNAME_PARAM)
            .buildAndExpand(ApiPaths.USERNAME_PARAM, response.getUsername())
            .toUri();
    return created(location, response);
  }

  @PostMapping(ApiPaths.LOGIN)
  public ResponseEntity<TokenResponse> login(@RequestBody LoginForm form) {
    return ok(authService.login(form));
  }
}
