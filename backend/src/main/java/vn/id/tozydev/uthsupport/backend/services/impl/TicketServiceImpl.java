package vn.id.tozydev.uthsupport.backend.services.impl;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
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
  public Iterable<TicketResponse> findAllWithUser(String username, boolean assigned) {
    return assigned
        ? ticketMapper.toResponses(ticketRepository.findAllAssignedBy(username))
        : ticketMapper.toResponses(ticketRepository.findAllByCreatedBy_Username(username));
  }

  @Override
  public Optional<TicketResponse> findOne(Long id) {
    return ticketRepository.findById(id).map(ticketMapper::toResponse);
  }

  @Override
  public Optional<TicketResponse> findOneWithUser(String username, Long id) {
    return ticketRepository
        .findById(id)
        .filter(ticket -> ticket.isOwned(username) || ticket.isAssigned(username))
        .map(ticketMapper::toResponse);
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
    return ticketOpt.flatMap(ticket -> update(request, ticket));
  }

  @Override
  public Optional<TicketResponse> updateWithUser(
      String username, Long id, UpdateTicketRequest request) {
    var ticketOpt = ticketRepository.findById(id);
    return ticketOpt
        .filter(ticket -> ticket.isOwned(username) || ticket.isAssigned(username))
        .flatMap(ticket -> update(request, ticket));
  }

  private Optional<TicketResponse> update(UpdateTicketRequest request, Ticket ticket) {
    Optional<Category> category = Optional.empty();
    if (request.getCategoryId() != null) {
      category = categoryRepository.findById(request.getCategoryId());
      if (category.isEmpty()) {
        throw new CategoryNotFoundException(
            "Cannot find category with id '" + request.getCategoryId() + "'");
      }
    }

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
    return ticketOpt.flatMap(ticket -> updateStatus(request, ticket));
  }

  @Override
  public Optional<TicketResponse> updateStatusWithUser(
      String username, Long id, UpdateTicketStatusRequest request) {
    var ticketOpt = ticketRepository.findById(id);
    if (ticketOpt.isEmpty()) {
      return Optional.empty();
    }
    var ticket = ticketOpt.get();
    if (ticket.isAssigned(username)) {
      return updateStatus(request, ticket);
    }

    throw new AuthorizationDeniedException("You don't have permission to update this ticket");
  }

  private Optional<TicketResponse> updateStatus(UpdateTicketStatusRequest request, Ticket ticket) {
    if (request.getStatus() == TicketStatus.PENDING) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Update pending status is not allowed!");
    }

    ticket.setStatus(request.getStatus());
    ticketRepository.save(ticket);

    return Optional.of(ticketMapper.toResponse(ticket));
  }
}
