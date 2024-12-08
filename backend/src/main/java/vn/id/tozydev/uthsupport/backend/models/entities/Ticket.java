package vn.id.tozydev.uthsupport.backend.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tickets")
@EntityListeners(AuditingEntityListener.class)
public class Ticket extends BaseEntity {
  @Column(nullable = false)
  private String subject;

  private String description;

  @Column(nullable = false)
  private TicketStatus status = TicketStatus.PENDING;
}
