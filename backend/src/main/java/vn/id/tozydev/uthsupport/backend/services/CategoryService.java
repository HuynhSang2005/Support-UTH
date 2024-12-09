package vn.id.tozydev.uthsupport.backend.services;

import vn.id.tozydev.uthsupport.backend.models.dtos.category.CategoryResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CreateCategoryRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.UpdateCategoryRequest;

import java.util.Optional;

public interface CategoryService {
  Iterable<CategoryResponse> findAll();

  Optional<CategoryResponse> findOne(Long id);

  CategoryResponse create(CreateCategoryRequest request);

  Optional<CategoryResponse> update(Long id, UpdateCategoryRequest request);

  void delete(Long id);
}
