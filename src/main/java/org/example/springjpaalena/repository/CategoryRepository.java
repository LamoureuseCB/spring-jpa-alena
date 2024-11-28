package org.example.springjpaalena.repository;

import org.example.springjpaalena.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
