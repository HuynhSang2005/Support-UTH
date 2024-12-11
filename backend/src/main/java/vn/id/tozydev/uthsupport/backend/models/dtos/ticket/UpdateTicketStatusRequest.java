package vn.id.tozydev.uthsupport.backend.models.dtos.ticket;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import vn.id.tozydev.uthsupport.backend.models.enums.TicketStatus;

@Data
public class UpdateTicketStatusRequest {
  @NotNull private TicketStatus status;
}
