package vn.id.tozydev.uthsupport.backend.models.dtos.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCategoryRequest {
  @NotBlank
  @Size(min = 3, max = 128)
  private String name;

  private String description;
}
