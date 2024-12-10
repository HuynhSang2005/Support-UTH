package vn.id.tozydev.uthsupport.backend.models.dtos.ticket;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTicketRequest {
  @NotBlank
  @Size(min = 3, max = 128)
  private String subject;

  @NotBlank private String comment;

  @NotNull
  @Min(1)
  private Long categoryId;
}
