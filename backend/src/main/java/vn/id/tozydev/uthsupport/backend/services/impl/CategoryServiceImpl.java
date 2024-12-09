package vn.id.tozydev.uthsupport.backend.services.impl;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CategoryResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CreateCategoryRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.UpdateCategoryRequest;
import vn.id.tozydev.uthsupport.backend.models.mappers.CategoryMapper;
import vn.id.tozydev.uthsupport.backend.repositories.CategoryRepository;
import vn.id.tozydev.uthsupport.backend.services.CategoryService;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final CategoryMapper categoryMapper;
  private final CategoryRepository categoryRepository;

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
}
