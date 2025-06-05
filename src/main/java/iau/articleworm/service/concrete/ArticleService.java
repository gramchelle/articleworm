package iau.articleworm.service.concrete;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iau.articleworm.repository.ArticleRepository;
import iau.articleworm.repository.CategoryRepository;
import iau.articleworm.repository.UserRepository;
import iau.articleworm.service.abstracts.IArticleService;
import lombok.RequiredArgsConstructor;
import iau.articleworm.dto.Article.*;
import iau.articleworm.model.Article;
import iau.articleworm.model.Category;
import iau.articleworm.model.Reaction;
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

        User user = userRepository.findById(dto.getAuthorId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        article.setUser(user);

        Category category = categoryRepository.findById(dto.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));
        article.setCategory(category);

        articleRepository.save(article);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleDto> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                    .map(this::convertToDto)
                    .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleDto getArticleById(Long id) {
        Article article = articleRepository.findById(id.intValue())
            .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
        return convertToDto(article);
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


    public ArticleDto convertToDto(Article article) {
        ArticleDto dto = new ArticleDto();
        dto.setId(article.getArticle_id().longValue());
        dto.setTitle(article.getTitle());
        dto.setContent(article.getContent());
        dto.setCategory(article.getCategory().getCategoryName());

        List<String> reactionTypes = article.getReactions()
            .stream()
            .map(reaction -> reaction.getType().toString()) // or .name() if it's an enum
            .collect(Collectors.toList());
        dto.setReactions(reactionTypes);

        return dto;
    }

}
