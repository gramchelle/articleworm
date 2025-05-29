package iau.articleworm.service.abstracts;

import java.util.List;

import iau.articleworm.dto.ArticleUpdateDto;
import iau.articleworm.model.Article;

public interface IArticleService {

    List<Article> getAllArticles();
    Article getArticleById(Long id);
    Article createArticle(Article article);
    Article updateArticle(Long article_id, ArticleUpdateDto articleUpdateDto);
    void deleteArticle(Long id);

}
