package vn.id.tozydev.uthsupport.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.id.tozydev.uthsupport.backend.models.entities.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {}
