package vn.id.tozydev.uthsupport.backend.models.dtos.category;

import lombok.Data;

import java.time.Instant;

@Data
public class CategoryResponse {
  private Long id;
  private String name;
  private String description;
  private Instant createdAt;
  private Instant updatedAt;
}
