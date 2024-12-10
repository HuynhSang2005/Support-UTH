package vn.id.tozydev.uthsupport.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vn.id.tozydev.uthsupport.backend.models.entities.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
  Iterable<Ticket> findAllByCreatedBy_Username(String createdByUsername);

  @Query(
      "select t from Ticket t join t.category c join fetch c.assignees a on a.username == :username")
  Iterable<Ticket> findAllAssignedBy(String username);
}
