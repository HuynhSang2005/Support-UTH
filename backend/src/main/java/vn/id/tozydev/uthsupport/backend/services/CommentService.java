package vn.id.tozydev.uthsupport.backend.services;

import java.util.Optional;
import vn.id.tozydev.uthsupport.backend.models.dtos.comment.CommentResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.comment.CreateCommentRequest;

public interface CommentService {
  Iterable<CommentResponse> findAll();

  Iterable<CommentResponse> findAllByTicketWithUser(String username, Long ticketId);

  Optional<CommentResponse> findOne(Long commentId);

  Optional<CommentResponse> findOneWithUser(String username, Long commentId);

  CommentResponse create(Long ticketId, CreateCommentRequest request);

  CommentResponse createWithUser(String username, Long ticketId, CreateCommentRequest request);
}
