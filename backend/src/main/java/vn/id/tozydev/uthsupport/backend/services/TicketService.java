package vn.id.tozydev.uthsupport.backend.services;

import java.util.Optional;

import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.CreateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.TicketResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.UpdateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.UpdateTicketStatusRequest;

public interface TicketService {
  Iterable<TicketResponse> findAll();

  Optional<TicketResponse> findOne(Long id);

  TicketResponse create(CreateTicketRequest request);

  Optional<TicketResponse> update(Long id, UpdateTicketRequest request);

  void delete(Long id);

  Optional<TicketResponse> updateStatus(Long id, UpdateTicketStatusRequest request);
}
