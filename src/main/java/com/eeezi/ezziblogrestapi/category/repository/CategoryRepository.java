package com.eeezi.ezziblogrestapi.category.repository;

import com.eeezi.ezziblogrestapi.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
