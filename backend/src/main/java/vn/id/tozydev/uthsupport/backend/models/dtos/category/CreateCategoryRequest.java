package vn.id.tozydev.uthsupport.backend.models.dtos.category;

import lombok.Data;

@Data
public class CreateCategoryRequest {
  private String name;
  private String description;
}
