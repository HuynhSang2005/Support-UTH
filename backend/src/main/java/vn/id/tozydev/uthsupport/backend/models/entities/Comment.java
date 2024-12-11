package vn.id.tozydev.uthsupport.backend.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@ToString
@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
public class Comment extends BaseEntity {
  @Column(nullable = false)
  private String content;

  @ManyToOne private Ticket ticket;

  @CreatedBy @ManyToOne private User createdBy;

  public boolean isOwned(String username) {
    return createdBy.getUsername().equals(username);
  }
}
