package vn.id.tozydev.uthsupport.backend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;

@Setter
@Getter
@ToString
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
  @Column(nullable = false)
  private String content;

  @ManyToOne private Ticket ticket;

  @CreatedBy @ManyToOne private User createdBy;
}
