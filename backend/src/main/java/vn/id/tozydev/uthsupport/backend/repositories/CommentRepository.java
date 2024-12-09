package vn.id.tozydev.uthsupport.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.id.tozydev.uthsupport.backend.models.entities.Comment;
import vn.id.tozydev.uthsupport.backend.models.entities.Ticket;

public interface CommentRepository extends CrudRepository<Comment, Long> {
  Iterable<Comment> findAllByTicket(Ticket ticket);
}
