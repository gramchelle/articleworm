package iau.articleworm.service.concrete;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import iau.articleworm.repository.ArticleRepository;
import iau.articleworm.repository.CategoryRepository;
import iau.articleworm.repository.UserRepository;
import iau.articleworm.service.abstracts.IArticleService;
import lombok.RequiredArgsConstructor;
import iau.articleworm.dto.Article.*;
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
    public Article updateArticle(Long article_id, ArticleUpdateDto articleUpdateDto) {
        Article existingArticle = articleRepository.findById(article_id.intValue())
            .orElseThrow(() -> new RuntimeException("Article not found with id: " + article_id));

        if (articleUpdateDto.getTitle() != null) {
            existingArticle.setTitle(articleUpdateDto.getTitle());
        }

        if (articleUpdateDto.getContent() != null) {
            existingArticle.setContent(articleUpdateDto.getContent());
        }
/*
        if (articleUpdateDto.getCategory() != null) {
            existingArticle.setCategory(articleUpdateDto.getCategory());
        }
*/
        existingArticle.setUpdatedAt(LocalDateTime.now()); // Güncellenme zamanı da elle set edebilirsin

        return articleRepository.save(existingArticle);
    }

    @Override
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id.intValue())
            .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
        articleRepository.delete(article);
    }
    

}
