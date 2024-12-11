package vn.id.tozydev.uthsupport.backend.services;

import java.util.Optional;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.CreateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.TicketResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.UpdateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.UpdateTicketStatusRequest;

public interface TicketService {
  Iterable<TicketResponse> findAll();

  Iterable<TicketResponse> findAllWithUser(String username, boolean assigned);

  Optional<TicketResponse> findOne(Long id);

  Optional<TicketResponse> findOneWithUser(String username, Long id);

  TicketResponse create(CreateTicketRequest request);

  Optional<TicketResponse> update(Long id, UpdateTicketRequest request);

  Optional<TicketResponse> updateWithUser(String username, Long id, UpdateTicketRequest request);

  Optional<TicketResponse> updateStatus(Long id, UpdateTicketStatusRequest request);

  Optional<TicketResponse> updateStatusWithUser(
      String username, Long id, UpdateTicketStatusRequest request);

  void delete(Long id);
}
