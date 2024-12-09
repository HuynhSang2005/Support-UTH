package vn.id.tozydev.uthsupport.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.id.tozydev.uthsupport.backend.models.dtos.comment.CommentResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.comment.CreateCommentRequest;
import vn.id.tozydev.uthsupport.backend.services.CommentService;

@RestController
@RequestMapping(ApiPaths.COMMENTS)
@AllArgsConstructor
public class CommentController extends BaseController {
  private final CommentService commentService;

  @GetMapping
  public ResponseEntity<Iterable<CommentResponse>> findAll() {
    return ok(commentService.findAll(null));
  }

  @GetMapping(ApiPaths.COMMENT_ID_PARAM)
  public ResponseEntity<CommentResponse> findOne(@PathVariable Long commentId) {
    return of(commentService.findOne(commentId));
  }

  @GetMapping(ApiPaths.TICKET_COMMENT)
  public ResponseEntity<Iterable<CommentResponse>> findAllByTicket(@PathVariable Long ticketId) {
    return ok(commentService.findAll(ticketId));
  }

  @PostMapping(ApiPaths.TICKET_COMMENT)
  public ResponseEntity<CommentResponse> create(
      @PathVariable Long ticketId,
      @RequestBody CreateCommentRequest request,
      UriComponentsBuilder ucb) {
    var response = commentService.create(ticketId, request);
    var location =
        ucb.pathSegment(ApiPaths.COMMENTS, ApiPaths.COMMENT_ID_PARAM)
            .buildAndExpand(ApiPaths.COMMENT_ID_PARAM, response.getId())
            .toUri();
    return created(location, response);
  }
}
