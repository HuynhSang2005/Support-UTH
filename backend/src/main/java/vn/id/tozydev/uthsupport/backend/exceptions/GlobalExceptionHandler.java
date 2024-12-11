package vn.id.tozydev.uthsupport.backend.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(CategoryNotFoundException.class)
  public ResponseEntity<Object> handleCategoryNotFound(CategoryNotFoundException ex) {
    var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    return ResponseEntity.badRequest().body(problemDetails);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      @NonNull MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    var fieldErrors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(
                fieldError ->
                    new FieldError(fieldError.getField(), fieldError.getDefaultMessage()));

    ex.getBody().setProperty("errors", fieldErrors);
    return handleExceptionInternal(ex, ex.getBody(), headers, HttpStatus.BAD_REQUEST, request);
  }

  record FieldError(String field, String message) {}
}
