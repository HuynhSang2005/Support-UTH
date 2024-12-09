package vn.id.tozydev.uthsupport.backend.models.entities;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Set;
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

  @ManyToMany
  @JoinTable(name = "category_assignees")
  @ToString.Exclude
  private Set<User> assignees;

  public void addAssignees(Collection<User> users) {
    assignees.addAll(users);
  }

  public void removeAssignees(Collection<String> usernames) {
    assignees.removeIf(user -> usernames.contains(user.getUsername()));
  }
}
