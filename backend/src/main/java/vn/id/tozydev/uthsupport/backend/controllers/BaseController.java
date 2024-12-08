package vn.id.tozydev.uthsupport.backend.controllers;

import java.net.URI;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

abstract class BaseController {
  static <T> ResponseEntity<T> ok(T body) {
    return ResponseEntity.ok(body);
  }

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  static <T> ResponseEntity<T> of(Optional<T> bodyOpt) {
    return ResponseEntity.of(bodyOpt);
  }

  static <T> ResponseEntity<T> created(URI location, T body) {
    return ResponseEntity.created(location).body(body);
  }

  static <T> ResponseEntity<T> notFound() {
    return ResponseEntity.notFound().build();
  }

  static <T> ResponseEntity<T> noContent() {
    return ResponseEntity.noContent().build();
  }
}
