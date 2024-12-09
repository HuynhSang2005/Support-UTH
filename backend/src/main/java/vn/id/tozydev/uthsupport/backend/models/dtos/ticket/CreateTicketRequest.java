package vn.id.tozydev.uthsupport.backend.models.dtos.ticket;

import lombok.Data;

@Data
public class CreateTicketRequest {
  private String subject;
  private String comment;
  private Long categoryId;
}
