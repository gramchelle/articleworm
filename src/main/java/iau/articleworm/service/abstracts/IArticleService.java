package iau.articleworm.service.abstracts;

import java.util.List;

import iau.articleworm.model.Article;

public interface IArticleService {

    List<Article> getAllArticles();
    Article getArticleById(Long id);
    Article createArticle(Article article);
    Article updateArticle(Long id, Article updatedArticle);
    void deleteArticle(Long id);

}
