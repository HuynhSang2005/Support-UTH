package vn.id.tozydev.uthsupport.backend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.AssigneesRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CategoryResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CreateCategoryRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.UpdateCategoryRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;
import vn.id.tozydev.uthsupport.backend.security.annotations.AdminOnly;
import vn.id.tozydev.uthsupport.backend.services.CategoryService;

@RestController
@RequestMapping(ApiPaths.CATEGORIES)
@AllArgsConstructor
public class CategoryController extends BaseController {
  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<Iterable<CategoryResponse>> findAll() {
    return ok(categoryService.findAll());
  }

  @GetMapping(ApiPaths.CATEGORY_ID_PARAM)
  public ResponseEntity<CategoryResponse> findOne(@PathVariable Long categoryId) {
    return of(categoryService.findOne(categoryId));
  }

  @AdminOnly
  @PostMapping
  public ResponseEntity<CategoryResponse> create(
      @Valid @RequestBody CreateCategoryRequest request, UriComponentsBuilder ucb) {
    var response = categoryService.create(request);
    var location =
        ucb.pathSegment(ApiPaths.CATEGORIES, ApiPaths.CATEGORY_ID_PARAM)
            .buildAndExpand(ApiPaths.CATEGORY_ID_PARAM, response.getId())
            .toUri();
    return created(response, location);
  }

  @AdminOnly
  @PatchMapping(ApiPaths.CATEGORY_ID_PARAM)
  public ResponseEntity<CategoryResponse> update(
      @PathVariable Long categoryId, @Valid @RequestBody UpdateCategoryRequest request) {
    return of(categoryService.update(categoryId, request));
  }

  @AdminOnly
  @DeleteMapping(ApiPaths.CATEGORY_ID_PARAM)
  public ResponseEntity<Void> delete(@PathVariable Long categoryId) {
    categoryService.delete(categoryId);
    return noContent();
  }

  @AdminOnly
  @GetMapping(ApiPaths.CATEGORY_ASSIGNEES)
  public ResponseEntity<Iterable<UserResponse>> findAllAssignees(@PathVariable Long categoryId) {
    return ok(categoryService.findAllAssignees(categoryId));
  }

  @AdminOnly
  @PostMapping(ApiPaths.CATEGORY_ASSIGNEES)
  public ResponseEntity<Iterable<UserResponse>> addAssignees(
      @PathVariable Long categoryId, @RequestBody AssigneesRequest request) {
    if (request.getAssignees().isEmpty()) {
      return notModified(categoryService.findAllAssignees(categoryId));
    }

    return created(categoryService.addAssignees(categoryId, request));
  }

  @AdminOnly
  @DeleteMapping(ApiPaths.CATEGORY_ASSIGNEES)
  public ResponseEntity<Iterable<UserResponse>> removeAssignees(
      @PathVariable Long categoryId, @RequestBody AssigneesRequest request) {
    if (request.getAssignees().isEmpty()) {
      return notModified(categoryService.findAllAssignees(categoryId));
    }

    return ok(categoryService.removeAssignees(categoryId, request));
  }
}
