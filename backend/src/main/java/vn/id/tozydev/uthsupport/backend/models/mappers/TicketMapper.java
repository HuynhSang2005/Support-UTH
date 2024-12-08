package vn.id.tozydev.uthsupport.backend.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.CreateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.TicketResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.UpdateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.entities.Ticket;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketMapper {
  TicketResponse toResponse(Ticket entity);

  Iterable<TicketResponse> toResponses(Iterable<Ticket> tickets);

  Ticket toEntity(CreateTicketRequest request);

  void update(UpdateTicketRequest request, @MappingTarget Ticket entity);
}
