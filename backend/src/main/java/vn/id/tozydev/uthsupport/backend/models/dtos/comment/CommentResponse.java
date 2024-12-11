package vn.id.tozydev.uthsupport.backend.models.dtos.comment;

import java.time.Instant;
import lombok.Data;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.TicketResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;

@Data
public class CommentResponse {
  private Long id;
  private String content;
  private TicketResponse ticket;
  private UserResponse createdBy;
  private Instant createdAt;
}
