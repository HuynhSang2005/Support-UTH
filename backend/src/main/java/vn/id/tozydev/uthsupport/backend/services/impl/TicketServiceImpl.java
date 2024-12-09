package vn.id.tozydev.uthsupport.backend.services.impl;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vn.id.tozydev.uthsupport.backend.exceptions.CategoryNotFoundException;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.CreateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.TicketResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.UpdateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.UpdateTicketStatusRequest;
import vn.id.tozydev.uthsupport.backend.models.entities.Category;
import vn.id.tozydev.uthsupport.backend.models.entities.Comment;
import vn.id.tozydev.uthsupport.backend.models.entities.Ticket;
import vn.id.tozydev.uthsupport.backend.models.enums.TicketStatus;
import vn.id.tozydev.uthsupport.backend.models.mappers.TicketMapper;
import vn.id.tozydev.uthsupport.backend.repositories.CategoryRepository;
import vn.id.tozydev.uthsupport.backend.repositories.CommentRepository;
import vn.id.tozydev.uthsupport.backend.repositories.TicketRepository;
import vn.id.tozydev.uthsupport.backend.services.TicketService;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
  private final TicketMapper ticketMapper;
  private final TicketRepository ticketRepository;
  private final CategoryRepository categoryRepository;
  private final CommentRepository commentRepository;

  @Override
  public Iterable<TicketResponse> findAll() {
    return ticketMapper.toResponses(ticketRepository.findAll());
  }

  @Override
  public Optional<TicketResponse> findOne(Long id) {
    return ticketRepository.findById(id).map(ticketMapper::toResponse);
  }

  @Override
  public TicketResponse create(CreateTicketRequest request) {
    var category = categoryRepository.findById(request.getCategoryId());
    if (category.isEmpty()) {
      throw new CategoryNotFoundException(
          "Cannot find category with id '" + request.getCategoryId() + "'");
    }

    var ticket = ticketMapper.toEntity(request);
    ticket.setCategory(category.get());
    ticketRepository.save(ticket);
    createComment(ticket, request.getComment());
    return ticketMapper.toResponse(ticket);
  }

  private void createComment(Ticket ticket, String content) {
    var comment = new Comment();
    comment.setContent(content);
    comment.setTicket(ticket);
    commentRepository.save(comment);
  }

  @Override
  public Optional<TicketResponse> update(Long id, UpdateTicketRequest request) {
    var ticketOpt = ticketRepository.findById(id);
    if (ticketOpt.isEmpty()) {
      return Optional.empty();
    }

    Optional<Category> category = Optional.empty();
    if (request.getCategoryId() != null) {
      category = categoryRepository.findById(request.getCategoryId());
      if (category.isEmpty()) {
        throw new CategoryNotFoundException(
            "Cannot find category with id '" + request.getCategoryId() + "'");
      }
    }

    var ticket = ticketOpt.get();
    ticketMapper.update(request, ticket);
    category.ifPresent(ticket::setCategory);
    ticketRepository.save(ticket);
    return Optional.of(ticketMapper.toResponse(ticket));
  }

  @Override
  public void delete(Long id) {
    ticketRepository.deleteById(id);
  }

  @Override
  public Optional<TicketResponse> updateStatus(Long id, UpdateTicketStatusRequest request) {
    var ticketOpt = ticketRepository.findById(id);
    if (ticketOpt.isEmpty()) {
      return Optional.empty();
    }

    if (request.getStatus() == TicketStatus.PENDING) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Update pending status is not allowed!");
    }

    var ticket = ticketOpt.get();
    ticket.setStatus(request.getStatus());
    ticketRepository.save(ticket);

    return Optional.of(ticketMapper.toResponse(ticket));
  }
}
