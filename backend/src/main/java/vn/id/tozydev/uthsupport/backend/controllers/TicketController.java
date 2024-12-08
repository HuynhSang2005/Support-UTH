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
@RequestMapping(ApiPaths.TICKETS)
@AllArgsConstructor
public class TicketController extends BaseController {
  private final TicketService ticketService;

  @GetMapping
  public ResponseEntity<Iterable<TicketResponse>> findAll() {
    return ok(ticketService.findAll());
  }

  @GetMapping(ApiPaths.TICKET_ID_PARAM)
  public ResponseEntity<TicketResponse> findOne(@PathVariable Long ticketId) {
    return of(ticketService.findOne(ticketId));
  }

  @PostMapping
  public ResponseEntity<TicketResponse> create(
      @RequestBody CreateTicketRequest request, UriComponentsBuilder ucb) {
    var response = ticketService.create(request);
    var location =
        ucb.pathSegment(ApiPaths.TICKETS, ApiPaths.TICKET_ID_PARAM)
            .buildAndExpand(ApiPaths.TICKET_ID_PARAM, response.getId())
            .toUri();
    return created(location, response);
  }

  @PatchMapping(ApiPaths.TICKET_ID_PARAM)
  public ResponseEntity<TicketResponse> update(
      @PathVariable Long ticketId, @RequestBody UpdateTicketRequest request) {
    return of(ticketService.update(ticketId, request));
  }

  @PatchMapping(ApiPaths.TICKET_STATUS)
  public ResponseEntity<TicketResponse> updateStatus(
      @PathVariable Long ticketId, @RequestBody UpdateTicketStatusRequest request) {
    return of(ticketService.updateStatus(ticketId, request));
  }

  @DeleteMapping(ApiPaths.TICKET_ID_PARAM)
  public ResponseEntity<Void> delete(@PathVariable Long ticketId) {
    ticketService.delete(ticketId);
    return noContent();
  }
}
