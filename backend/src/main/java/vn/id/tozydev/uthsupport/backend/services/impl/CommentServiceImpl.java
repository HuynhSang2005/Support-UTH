package vn.id.tozydev.uthsupport.backend.services.impl;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vn.id.tozydev.uthsupport.backend.exceptions.TicketNotFoundException;
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
  public Iterable<CommentResponse> findAll(Long ticketId) {
    Optional<Ticket> ticket = Optional.empty();
    if (ticketId != null) {
      ticket = ticketRepository.findById(ticketId);
      if (ticket.isEmpty()) {
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Ticket with id '" + ticketId + "' not found!");
      }
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
  public CommentResponse create(Long ticketId, CreateCommentRequest request) {
    var ticket = ticketRepository.findById(ticketId);
    if (ticket.isEmpty()) {
      throw new TicketNotFoundException("Ticket with id '" + ticketId + "' not found!");
    }

    var comment = commentMapper.toEntity(request);
    comment.setTicket(ticket.get());
    commentRepository.save(comment);
    return commentMapper.toResponse(comment);
  }
}
