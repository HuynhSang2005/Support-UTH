package vn.id.tozydev.uthsupport.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.CreateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.TicketResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.UpdateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.UpdateTicketStatusRequest;
import vn.id.tozydev.uthsupport.backend.services.TicketService;

@RestController
@RequestMapping("/api/v1/tickets")
@AllArgsConstructor
public class TicketController extends BaseController {
  private final TicketService ticketService;

  @GetMapping
  public ResponseEntity<Iterable<TicketResponse>> findAll() {
    return ok(ticketService.findAll());
  }

  @GetMapping("{ticketId}")
  public ResponseEntity<TicketResponse> findOne(@PathVariable Long ticketId) {
    return of(ticketService.findOne(ticketId));
  }

  @PostMapping
  public ResponseEntity<TicketResponse> create(
      @RequestBody CreateTicketRequest request, UriComponentsBuilder ucb) {
    var response = ticketService.create(request);
    var location =
        ucb.path("/api/v1/tickets/{ticketId}")
            .buildAndExpand("{ticketId}", response.getId())
            .toUri();
    return created(location, response);
  }

  @PatchMapping("{ticketId}")
  public ResponseEntity<TicketResponse> update(
      @PathVariable Long ticketId, @RequestBody UpdateTicketRequest request) {
    return of(ticketService.update(ticketId, request));
  }

  @PatchMapping("{ticketId}/status")
  public ResponseEntity<TicketResponse> updateStatus(
      @PathVariable Long ticketId, @RequestBody UpdateTicketStatusRequest request) {
    return of(ticketService.updateStatus(ticketId, request));
  }

  @DeleteMapping("{ticketId}")
  public ResponseEntity<Void> delete(@PathVariable Long ticketId) {
    ticketService.delete(ticketId);
    return noContent();
  }
}
