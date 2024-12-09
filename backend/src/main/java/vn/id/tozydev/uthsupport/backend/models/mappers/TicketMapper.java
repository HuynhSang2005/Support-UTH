package vn.id.tozydev.uthsupport.backend.models.mappers;

import java.util.ArrayList;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.CreateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.TicketResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.UpdateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.entities.Ticket;
import vn.id.tozydev.uthsupport.backend.repositories.CommentRepository;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserMapper.class, CategoryMapper.class})
@DecoratedWith(TicketMapper.Decorator.class)
public interface TicketMapper {
  TicketResponse toResponse(Ticket entity);

  Iterable<TicketResponse> toResponses(Iterable<Ticket> tickets);

  Ticket toEntity(CreateTicketRequest request);

  void update(UpdateTicketRequest request, @MappingTarget Ticket entity);

  abstract class Decorator implements TicketMapper {
    @Autowired private TicketMapper delegate;
    @Autowired private CommentRepository commentRepository;

    @Override
    public TicketResponse toResponse(Ticket entity) {
      var response = delegate.toResponse(entity);
      response.setComments(commentRepository.countCommentByTicket(entity));
      return response;
    }

    @Override
    public Iterable<TicketResponse> toResponses(Iterable<Ticket> tickets) {
      if (tickets == null) {
        return null;
      }

      var list = new ArrayList<TicketResponse>();
      for (Ticket ticket : tickets) {
        list.add(toResponse(ticket));
      }

      return list;
    }
  }
}
