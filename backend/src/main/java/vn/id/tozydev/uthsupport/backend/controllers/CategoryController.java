package vn.id.tozydev.uthsupport.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CategoryResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CreateCategoryRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.UpdateCategoryRequest;
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

  @PostMapping
  public ResponseEntity<CategoryResponse> create(
      @RequestBody CreateCategoryRequest request, UriComponentsBuilder ucb) {
    var response = categoryService.create(request);
    var location =
        ucb.pathSegment(ApiPaths.CATEGORIES, ApiPaths.CATEGORY_ID_PARAM)
            .buildAndExpand(ApiPaths.CATEGORY_ID_PARAM, response.getId())
            .toUri();
    return created(location, response);
  }

  @PatchMapping(ApiPaths.CATEGORY_ID_PARAM)
  public ResponseEntity<CategoryResponse> update(
      @PathVariable Long categoryId, @RequestBody UpdateCategoryRequest request) {
    return of(categoryService.update(categoryId, request));
  }

  @DeleteMapping(ApiPaths.CATEGORY_ID_PARAM)
  public ResponseEntity<Void> delete(@PathVariable Long categoryId) {
    categoryService.delete(categoryId);
    return noContent();
  }
}
