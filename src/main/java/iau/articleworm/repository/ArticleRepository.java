package iau.articleworm.entities.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iau.articleworm.entities.concretes.Article;

public interface ArticleDao extends JpaRepository<Article, Integer> { 

    List<GetAllArticlesDto> getAllArticles();

    Boolean addArticle(ArticleSaveDto article);
    
    Boolean deleteArticle(Long id);

    Boolean updateArticle(Long id, Article article);

    Article getArticleById(Long id);

    List<Article> getArticlesByUsername(String username);

}
