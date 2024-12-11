package vn.id.tozydev.uthsupport.backend.models.dtos.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCommentRequest {
  @NotBlank
  private String content;
}
