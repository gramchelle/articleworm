package iau.articleworm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import iau.articleworm.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByCategoryName(String categoryName);

    @Query("DELETE FROM Category c WHERE c.categoryName = ?1")
    void deleteByCategoryName(String categoryName);

    Optional<Category> findByCategoryName(String categoryName);
}
