package iau.articleworm.entities.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import iau.articleworm.entities.concretes.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    
}
