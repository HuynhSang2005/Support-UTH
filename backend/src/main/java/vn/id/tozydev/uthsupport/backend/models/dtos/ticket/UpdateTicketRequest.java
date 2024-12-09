package vn.id.tozydev.uthsupport.backend.models.dtos.ticket;

import lombok.Data;

@Data
public class UpdateTicketRequest {
  private String subject;
  private Long categoryId;
}
