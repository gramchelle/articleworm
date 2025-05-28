package iau.articleworm.entities.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import iau.articleworm.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
