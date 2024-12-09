package vn.id.tozydev.uthsupport.backend.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.id.tozydev.uthsupport.backend.models.enums.TicketStatus;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tickets")
@EntityListeners(AuditingEntityListener.class)
public class Ticket extends BaseEntity {
  @Column(nullable = false)
  private String subject;

  @Column(nullable = false)
  private TicketStatus status = TicketStatus.PENDING;

  @CreatedBy @ManyToOne private User createdBy;

  @LastModifiedBy @ManyToOne private User updatedBy;

  @ManyToOne private Category category;
}
