package vn.id.tozydev.uthsupport.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.EnumSet;
import lombok.Getter;

@Getter(onMethod_ = {@JsonValue})
public enum TicketStatus {
  PENDING,
  IN_PROGRESS,
  RESOLVED,
  CLOSED;

  private final String status;

  TicketStatus() {
    this.status = name().toLowerCase();
  }

  @JsonCreator
  public static TicketStatus fromStatus(String status) {
    return EnumSet.allOf(TicketStatus.class).stream()
        .filter(e -> e.status.equalsIgnoreCase(status))
        .findFirst()
        .orElseThrow();
  }
}
