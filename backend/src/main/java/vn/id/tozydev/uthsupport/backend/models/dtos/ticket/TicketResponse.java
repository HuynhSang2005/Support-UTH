package vn.id.tozydev.uthsupport.backend.models.dtos.ticket;

import java.time.Instant;
import lombok.Data;
import vn.id.tozydev.uthsupport.backend.models.entities.TicketStatus;

@Data
public class TicketResponse {
  private Long id;
  private String subject;
  private String description;
  private TicketStatus status;
  private Instant createdAt;
  private Instant updatedAt;
}
