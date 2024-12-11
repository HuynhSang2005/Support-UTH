package vn.id.tozydev.uthsupport.backend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.CreateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.TicketResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.UpdateTicketRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.ticket.UpdateTicketStatusRequest;
import vn.id.tozydev.uthsupport.backend.models.enums.UserRole;
import vn.id.tozydev.uthsupport.backend.security.annotations.AdminOnly;
import vn.id.tozydev.uthsupport.backend.services.TicketService;
import vn.id.tozydev.uthsupport.backend.swagger.RequireAuth;

@RestController
@RequestMapping(path = ApiPaths.TICKETS, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@RequireAuth
public class TicketController extends BaseController {
  private final TicketService ticketService;

  @GetMapping
  public ResponseEntity<Iterable<TicketResponse>> findAll(
      @RequestParam(required = false, defaultValue = "false") boolean assigned,
      Authentication authentication) {
    if (UserRole.ADMIN.hasAccess(authentication)) {
      return ok(ticketService.findAll());
    }

    return ok(ticketService.findAllWithUser(authentication.getName(), assigned));
  }

  @GetMapping(ApiPaths.TICKET_ID_PARAM)
  public ResponseEntity<TicketResponse> findOne(
      @PathVariable Long ticketId, Authentication authentication) {
    if (UserRole.ADMIN.hasAccess(authentication)) {
      return of(ticketService.findOne(ticketId));
    }

    return of(ticketService.findOneWithUser(authentication.getName(), ticketId));
  }

  @PostMapping
  public ResponseEntity<TicketResponse> create(
      @Valid @RequestBody CreateTicketRequest request, UriComponentsBuilder ucb) {
    var response = ticketService.create(request);
    var location =
        ucb.pathSegment(ApiPaths.TICKETS, ApiPaths.TICKET_ID_PARAM)
            .buildAndExpand(ApiPaths.TICKET_ID_PARAM, response.getId())
            .toUri();
    return created(response, location);
  }

  @PatchMapping(ApiPaths.TICKET_ID_PARAM)
  public ResponseEntity<TicketResponse> update(
      @PathVariable Long ticketId,
      @Valid @RequestBody UpdateTicketRequest request,
      Authentication authentication) {
    if (UserRole.ADMIN.hasAccess(authentication)) {
      return of(ticketService.update(ticketId, request));
    }

    return of(ticketService.updateWithUser(authentication.getName(), ticketId, request));
  }

  @PatchMapping(ApiPaths.TICKET_STATUS)
  public ResponseEntity<TicketResponse> updateStatus(
      @PathVariable Long ticketId,
      @Valid @RequestBody UpdateTicketStatusRequest request,
      Authentication authentication) {
    if (UserRole.ADMIN.hasAccess(authentication)) {
      return of(ticketService.updateStatus(ticketId, request));
    }

    return of(ticketService.updateStatusWithUser(authentication.getName(), ticketId, request));
  }

  @AdminOnly
  @DeleteMapping(ApiPaths.TICKET_ID_PARAM)
  public ResponseEntity<Void> delete(@PathVariable Long ticketId) {
    ticketService.delete(ticketId);
    return noContent();
  }
}
