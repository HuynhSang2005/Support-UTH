package vn.id.tozydev.uthsupport.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.CreateUserRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UpdateUserRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;
import vn.id.tozydev.uthsupport.backend.services.UserService;

@RestController
@RequestMapping(ApiPaths.USERS)
@AllArgsConstructor
public class UserController extends BaseController {
  private final UserService userService;

  @GetMapping
  public ResponseEntity<Iterable<UserResponse>> findAll() {
    return ok(userService.findAll());
  }

  @GetMapping(ApiPaths.USERNAME_PARAM)
  public ResponseEntity<UserResponse> findOne(@PathVariable String username) {
    return of(userService.findOne(username));
  }

  @PostMapping
  public ResponseEntity<UserResponse> create(
      @RequestBody CreateUserRequest request, UriComponentsBuilder ucb) {
    var response = userService.create(request);
    var location =
        ucb.pathSegment(ApiPaths.PREFIX, ApiPaths.USERS, ApiPaths.USERNAME_PARAM)
            .buildAndExpand(ApiPaths.USERNAME_PARAM, response.getUsername())
            .toUri();
    return created(location, response);
  }

  @PatchMapping(ApiPaths.USERNAME_PARAM)
  public ResponseEntity<UserResponse> update(
      @PathVariable String username, @RequestBody UpdateUserRequest request) {
    return of(userService.update(username, request));
  }

  @DeleteMapping(ApiPaths.USERNAME_PARAM)
  public ResponseEntity<Void> delete(@PathVariable String username) {
    userService.delete(username);
    return noContent();
  }
}
