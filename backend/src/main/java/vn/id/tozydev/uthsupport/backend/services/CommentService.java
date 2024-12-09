package vn.id.tozydev.uthsupport.backend.services;

import java.util.Optional;
import org.springframework.lang.Nullable;
import vn.id.tozydev.uthsupport.backend.models.dtos.comment.CommentResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.comment.CreateCommentRequest;

public interface CommentService {
  Iterable<CommentResponse> findAll(@Nullable Long ticketId);

  Optional<CommentResponse> findOne(Long commentId);

  CommentResponse create(Long ticketId, CreateCommentRequest request);
}
