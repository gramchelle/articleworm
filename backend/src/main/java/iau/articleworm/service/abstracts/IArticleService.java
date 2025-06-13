package iau.articleworm.service.abstracts;

import java.util.List;

import iau.articleworm.dto.Article.ArticleDto;
import iau.articleworm.dto.Article.ArticleUpdateDto;
import iau.articleworm.model.Article;

public interface IArticleService {

    List<ArticleDto> getAllArticles();
    ArticleDto getArticleById(Long id);
    Article createArticle(Article article);
    Article updateArticle(Long article_id, ArticleUpdateDto articleUpdateDto);
    void deleteArticle(Long id);
    List<Integer> getArticleIdsByCategoryId(Integer categoryId);


}
