package vn.id.tozydev.uthsupport.backend.services.impl;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vn.id.tozydev.uthsupport.backend.models.dtos.comment.CommentResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.comment.CreateCommentRequest;
import vn.id.tozydev.uthsupport.backend.models.entities.Ticket;
import vn.id.tozydev.uthsupport.backend.models.mappers.CommentMapper;
import vn.id.tozydev.uthsupport.backend.repositories.CommentRepository;
import vn.id.tozydev.uthsupport.backend.repositories.TicketRepository;
import vn.id.tozydev.uthsupport.backend.services.CommentService;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
  private final CommentMapper commentMapper;
  private final CommentRepository commentRepository;
  private final TicketRepository ticketRepository;

  @Override
  public Iterable<CommentResponse> findAll() {
    return commentMapper.toResponses(commentRepository.findAll());
  }

  @Override
  public Iterable<CommentResponse> findAllByTicketWithUser(String username, Long ticketId) {
    Optional<Ticket> ticket = ticketRepository.findById(ticketId);
    if (ticket.isEmpty() || !ticket.get().isOwned(username) || !ticket.get().isAssigned(username)) {
      throw createTicketNotFoundStatus(ticketId);
    }

    return ticket
        .map(commentRepository::findAllByTicket)
        .map(commentMapper::toResponses)
        .orElseGet(() -> commentMapper.toResponses(commentRepository.findAll()));
  }

  @Override
  public Optional<CommentResponse> findOne(Long commentId) {
    return commentRepository.findById(commentId).map(commentMapper::toResponse);
  }

  @Override
  public Optional<CommentResponse> findOneWithUser(String username, Long commentId) {
    return commentRepository
        .findById(commentId)
        .filter(
            comment ->
                comment.isOwned(username)
                    || comment.getTicket().isOwned(username)
                    || comment.getTicket().isAssigned(username))
        .map(commentMapper::toResponse);
  }

  @Override
  public CommentResponse create(Long ticketId, CreateCommentRequest request) {
    var ticket = ticketRepository.findById(ticketId);
    if (ticket.isEmpty()) {
      throw createTicketNotFoundStatus(ticketId);
    }

    return create(request, ticket.get());
  }

  @Override
  public CommentResponse createWithUser(
      String username, Long ticketId, CreateCommentRequest request) {
    var ticketOpt = ticketRepository.findById(ticketId);
    if (ticketOpt.isEmpty()
        || !ticketOpt.get().isOwned(username)
        || ticketOpt.get().isAssigned(username)) {
      throw createTicketNotFoundStatus(ticketId);
    }
    return create(request, ticketOpt.get());
  }

  private CommentResponse create(CreateCommentRequest request, Ticket ticket) {
    var comment = commentMapper.toEntity(request);
    comment.setTicket(ticket);
    commentRepository.save(comment);
    return commentMapper.toResponse(comment);
  }

  private ResponseStatusException createTicketNotFoundStatus(Long ticketId) {
    return new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Ticket with id '" + ticketId + "' not found!");
  }
}
