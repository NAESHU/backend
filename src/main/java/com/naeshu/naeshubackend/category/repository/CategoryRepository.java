package com.naeshu.naeshubackend.category.repository;

import com.naeshu.naeshubackend.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
