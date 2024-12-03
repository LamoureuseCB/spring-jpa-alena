package org.example.springjpaalena.repository;

import org.example.springjpaalena.model.Category;
import org.example.springjpaalena.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option,Integer> {
    List<Option> findAllByCategoryIn(List<Category> categoryIds);

}
