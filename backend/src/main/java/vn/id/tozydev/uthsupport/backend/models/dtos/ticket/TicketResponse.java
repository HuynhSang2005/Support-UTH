package vn.id.tozydev.uthsupport.backend.models.dtos.ticket;

import java.time.Instant;
import lombok.Data;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CategoryResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;
import vn.id.tozydev.uthsupport.backend.models.enums.TicketStatus;

@Data
public class TicketResponse {
  private Long id;
  private String subject;
  private TicketStatus status;
  private CategoryResponse category;
  private UserResponse createdBy;
  private Instant createdAt;
  private UserResponse updatedBy;
  private Instant updatedAt;
}
