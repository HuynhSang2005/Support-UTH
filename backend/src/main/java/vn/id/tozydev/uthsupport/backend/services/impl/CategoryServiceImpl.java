package vn.id.tozydev.uthsupport.backend.services.impl;

import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.AssigneesRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CategoryResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CreateCategoryRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.UpdateCategoryRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.user.UserResponse;
import vn.id.tozydev.uthsupport.backend.models.entities.Category;
import vn.id.tozydev.uthsupport.backend.models.mappers.CategoryMapper;
import vn.id.tozydev.uthsupport.backend.models.mappers.UserMapper;
import vn.id.tozydev.uthsupport.backend.repositories.CategoryRepository;
import vn.id.tozydev.uthsupport.backend.repositories.UserRepository;
import vn.id.tozydev.uthsupport.backend.services.CategoryService;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final CategoryMapper categoryMapper;
  private final UserMapper userMapper;
  private final CategoryRepository categoryRepository;
  private final UserRepository userRepository;

  @Override
  public Iterable<CategoryResponse> findAll() {
    return categoryMapper.toResponses(categoryRepository.findAll());
  }

  @Override
  public Optional<CategoryResponse> findOne(Long id) {
    return categoryRepository.findById(id).map(categoryMapper::toResponse);
  }

  @Override
  public CategoryResponse create(CreateCategoryRequest request) {
    var category = categoryMapper.toEntity(request);
    categoryRepository.save(category);
    return categoryMapper.toResponse(category);
  }

  @Override
  public Optional<CategoryResponse> update(Long id, UpdateCategoryRequest request) {
    var categoryOpt = categoryRepository.findById(id);
    if (categoryOpt.isEmpty()) {
      return Optional.empty();
    }

    var category = categoryOpt.get();
    categoryMapper.update(request, category);
    categoryRepository.save(category);
    return Optional.ofNullable(categoryMapper.toResponse(category));
  }

  @Override
  public void delete(Long id) {
    categoryRepository.deleteById(id);
  }

  @Override
  public Iterable<UserResponse> findAllAssignees(Long categoryId) {
    var category = getCategory(categoryId);
    return userMapper.toResponses(category.getAssignees());
  }

  @Override
  public Iterable<UserResponse> addAssignees(Long categoryId, AssigneesRequest request) {
    var category = getCategory(categoryId);
    var users =
        request.getAssignees().stream()
            .map(username -> userRepository.findByUsername(username).orElse(null))
            .filter(Objects::nonNull)
            .toList();
    category.addAssignees(users);
    categoryRepository.save(category);
    return userMapper.toResponses(category.getAssignees());
  }

  @Override
  public Iterable<UserResponse> removeAssignees(Long categoryId, AssigneesRequest request) {
    var category = getCategory(categoryId);
    category.removeAssignees(request.getAssignees());
    categoryRepository.save(category);
    return userMapper.toResponses(category.getAssignees());
  }

  private Category getCategory(Long categoryId) {
    var category = categoryRepository.findById(categoryId);
    if (category.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Category with id '" + categoryId + "' not found!");
    }
    return category.get();
  }
}
