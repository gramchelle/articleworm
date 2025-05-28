package iau.articleworm.entities.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iau.articleworm.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> { 

    List<GetAllArticlesDto> getAllArticles();

    Boolean addArticle(ArticleSaveDto article);
    
    Boolean deleteArticle(Long id);

    Boolean updateArticle(Long id, Article article);

    Article getArticleById(Long id);

    List<Article> getArticlesByUsername(String username);

}
