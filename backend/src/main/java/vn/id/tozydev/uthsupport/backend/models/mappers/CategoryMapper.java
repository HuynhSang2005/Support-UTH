package vn.id.tozydev.uthsupport.backend.models.mappers;

import org.mapstruct.*;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CategoryResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.CreateCategoryRequest;
import vn.id.tozydev.uthsupport.backend.models.dtos.category.UpdateCategoryRequest;
import vn.id.tozydev.uthsupport.backend.models.entities.Category;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
  CategoryResponse toResponse(Category category);

  Iterable<CategoryResponse> toResponses(Iterable<Category> categories);

  Category toEntity(CreateCategoryRequest request);

  void update(UpdateCategoryRequest request, @MappingTarget Category category);
}