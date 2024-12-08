package vn.id.tozydev.uthsupport.backend.models.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum UserRole implements GrantedAuthority {
  STUDENT,
  LECTURE,
  ADMIN;

  private final String authority;

  UserRole() {
    this.authority = "ROLE_" + name();
  }
}
