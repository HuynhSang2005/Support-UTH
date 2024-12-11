package vn.id.tozydev.uthsupport.backend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.id.tozydev.uthsupport.backend.models.dtos.comment.CommentResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.comment.CreateCommentRequest;
import vn.id.tozydev.uthsupport.backend.models.enums.UserRole;
import vn.id.tozydev.uthsupport.backend.security.annotations.AdminOnly;
import vn.id.tozydev.uthsupport.backend.services.CommentService;
import vn.id.tozydev.uthsupport.backend.swagger.RequireAuth;

@RestController
@AllArgsConstructor
@RequireAuth
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController extends BaseController {
  private final CommentService commentService;

  @AdminOnly
  @GetMapping(ApiPaths.COMMENTS)
  public ResponseEntity<Iterable<CommentResponse>> findAll() {
    return ok(commentService.findAll());
  }

  @GetMapping(ApiPaths.COMMENTS + "/" + ApiPaths.COMMENT_ID_PARAM)
  public ResponseEntity<CommentResponse> findOne(
      @PathVariable Long commentId, Authentication authentication) {
    if (UserRole.ADMIN.hasAccess(authentication)) {
      return of(commentService.findOne(commentId));
    }

    return of(commentService.findOneWithUser(authentication.getName(), commentId));
  }

  @GetMapping(ApiPaths.TICKET_COMMENT)
  public ResponseEntity<Iterable<CommentResponse>> findAllByTicket(
      @PathVariable Long ticketId, Authentication authentication) {
    return ok(commentService.findAllByTicketWithUser(authentication.getName(), ticketId));
  }

  @PostMapping(ApiPaths.TICKET_COMMENT)
  public ResponseEntity<CommentResponse> create(
      @PathVariable Long ticketId,
      @Valid @RequestBody CreateCommentRequest request,
      UriComponentsBuilder ucb,
      Authentication authentication) {
    CommentResponse response;
    if (UserRole.ADMIN.hasAccess(authentication)) {
      response = commentService.create(ticketId, request);
    } else {
      response = commentService.createWithUser(authentication.getName(), ticketId, request);
    }
    var location =
        ucb.pathSegment(ApiPaths.COMMENTS, ApiPaths.COMMENT_ID_PARAM)
            .buildAndExpand(ApiPaths.COMMENT_ID_PARAM, response.getId())
            .toUri();
    return created(response, location);
  }
}
