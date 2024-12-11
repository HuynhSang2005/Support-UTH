package vn.id.tozydev.uthsupport.backend.models.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
  @Id @GeneratedValue private Long id;
  @CreatedDate private Instant createdAt;
  @LastModifiedDate private Instant updatedAt;
}
