package vn.id.tozydev.uthsupport.backend.models.dtos.ticket;

import lombok.Data;
import vn.id.tozydev.uthsupport.backend.models.enums.TicketStatus;

@Data
public class UpdateTicketStatusRequest {
  private TicketStatus status;
}
