package vn.id.tozydev.uthsupport.backend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
  @Column(nullable = false)
  private String name;

  private String description;
}
