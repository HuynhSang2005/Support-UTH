package vn.id.tozydev.uthsupport.backend.models.dtos.category;

import lombok.Data;

@Data
public class UpdateCategoryRequest {
  private String name;
  private String description;
}
