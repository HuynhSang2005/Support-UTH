package vn.id.tozydev.uthsupport.backend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;
import vn.id.tozydev.uthsupport.backend.models.enums.UserRole;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private UserRole role = UserRole.STUDENT;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String fullName;

  public UserDetails toUserDetails() {
    return org.springframework.security.core.userdetails.User.builder()
        .username(username)
        .password(password)
        .authorities(role)
        .build();
  }
}
