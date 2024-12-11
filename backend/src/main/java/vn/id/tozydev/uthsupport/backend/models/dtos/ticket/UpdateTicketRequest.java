package vn.id.tozydev.uthsupport.backend.models.dtos.ticket;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateTicketRequest {
  @Size(min = 3, max = 128)
  private String subject;

  @Min(1)
  private Long categoryId;
}
