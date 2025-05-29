package iau.articleworm.service.concrete;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import iau.articleworm.repository.ArticleRepository;
import iau.articleworm.repository.CategoryRepository;
import iau.articleworm.repository.UserRepository;
import iau.articleworm.service.abstracts.IArticleService;
import lombok.RequiredArgsConstructor;
import iau.articleworm.dto.*;
import iau.articleworm.model.Article;
import iau.articleworm.model.Category;
import iau.articleworm.model.User;

@Service
@RequiredArgsConstructor
public class ArticleService implements IArticleService {
    
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    public boolean saveArticle(ArticleSaveDto dto) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());

        User user = userRepository.findById(dto.getAuthor_id())
            .orElseThrow(() -> new RuntimeException("User not found"));
        article.setUser(user);

        Category category = categoryRepository.findById(dto.getCategory_id())
            .orElseThrow(() -> new RuntimeException("Category not found"));
        article.setCategory(category);

        articleRepository.save(article);
        return true;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Long id, Article updatedArticle) {
        Article article = articleRepository.findById(id.intValue())
            .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));

        article.setTitle(updatedArticle.getTitle());
        article.setContent(updatedArticle.getContent());
        article.setUpdatedAt(updatedArticle.getUpdatedAt());
        // category de re-set edilebilir.

        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id.intValue())
            .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
        articleRepository.delete(article);
    }
    

}
