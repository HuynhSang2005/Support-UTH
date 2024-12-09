package vn.id.tozydev.uthsupport.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.id.tozydev.uthsupport.backend.models.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {}
