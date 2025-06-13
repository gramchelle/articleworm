package iau.articleworm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import iau.articleworm.model.Article;
import iau.articleworm.model.Category;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> { 

    Optional<Article> findByTitle(String title);
    List<Article> findAllByCategory(Category category);
    List<Article> findByCategory_CategoryId(Integer categoryId);
    List<Article> findByUser_UserId(Integer userId);

    List<Article> findByCategory_CategoryName(String categoryName);

}
