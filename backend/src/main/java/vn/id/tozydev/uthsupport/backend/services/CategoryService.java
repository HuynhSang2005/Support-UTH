package vn.id.tozydev.uthsupport.backend.services;

import java.util.Optional;

import vn.id.tozydev.uthsupport.backend.models.dtos.category.AssigneesRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CategoryResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CreateCategoryRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.UpdateCategoryRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;

public interface CategoryService {
  Iterable<CategoryResponse> findAll();

  Optional<CategoryResponse> findOne(Long id);

  CategoryResponse create(CreateCategoryRequest request);

  Optional<CategoryResponse> update(Long id, UpdateCategoryRequest request);

  void delete(Long id);

  Iterable<UserResponse> findAllAssignees(Long categoryId);

  Iterable<UserResponse> addAssignees(Long categoryId, AssigneesRequest request);

  Iterable<UserResponse> removeAssignees(Long categoryId, AssigneesRequest request);
}
