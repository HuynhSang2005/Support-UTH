package vn.id.tozydev.uthsupport.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UpdateUserRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;
import vn.id.tozydev.uthsupport.backend.services.UserService;

@RestController
@RequestMapping(ApiPaths.USER)
@AllArgsConstructor
public class LoggedInUserController extends BaseController {
  private final UserService userService;

  @GetMapping
  public ResponseEntity<UserResponse> findCurrent(Authentication authentication) {
    return of(userService.findOne(authentication.getName()));
  }

  @PatchMapping
  public ResponseEntity<UserResponse> updateCurrent(
      Authentication authentication, @RequestBody UpdateUserRequest request) {
    request.setRole(null);
    return of(userService.update(authentication.getName(), request));
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteCurrent(Authentication authentication) {
    userService.delete(authentication.getName());
    return noContent();
  }
}
