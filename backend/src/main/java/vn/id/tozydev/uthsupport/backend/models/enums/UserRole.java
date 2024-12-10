package vn.id.tozydev.uthsupport.backend.models.enums;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum UserRole implements GrantedAuthority {
  STUDENT,
  LECTURE,
  ADMIN;

  public static final String AUTHORITY_PREFIX = "ROLE_";
  private final String authority;

  UserRole() {
    this.authority = AUTHORITY_PREFIX + name();
  }

  public boolean hasAccess(Authentication authentication) {
    return authentication.getAuthorities().stream()
        .anyMatch(a -> a.getAuthority().equals(authority));
  }
}
