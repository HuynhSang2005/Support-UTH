package vn.id.tozydev.uthsupport.backend.models.dtos.ticket;

import lombok.Data;
import vn.id.tozydev.uthsupport.backend.models.entities.TicketStatus;

@Data
public class UpdateTicketStatusRequest {
  private TicketStatus status;
}
