package iau.articleworm.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iau.articleworm.dto.Article.ArticleDto;
import iau.articleworm.dto.Article.ArticleSaveDto;
import iau.articleworm.dto.Article.ArticleUpdateDto;
import iau.articleworm.model.Article;
import iau.articleworm.service.concrete.ArticleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    @GetMapping("/getAll")
    public ResponseEntity<List<ArticleDto>> getAllArticles() {
        List<ArticleDto> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/article/{id}")
    public ArticleDto getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping("/saveArticle")
    public ResponseEntity<Boolean> createArticle(@RequestBody ArticleSaveDto articleSaveDto) {
        Boolean articleSaved = articleService.saveArticle(articleSaveDto);
        return new ResponseEntity<>(articleSaved, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{article_id}/update")
    public ResponseEntity<String> updateArticle(
            @PathVariable Long article_id,
            @RequestBody ArticleUpdateDto articleUpdateDto) {
        
        Article updatedArticle = articleService.updateArticle(article_id, articleUpdateDto);
        if (updatedArticle != null) {
            return new ResponseEntity<>("Article updated successfully", HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/id/{categoryId}")
    public ResponseEntity<List<ArticleDto>> getArticlesByCategoryId(@PathVariable Integer categoryId) {
        List<ArticleDto> articles = articleService.getArticlesByCategoryId(categoryId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/category/name/{categoryName}")
    public ResponseEntity<List<ArticleDto>> getArticlesByCategoryName(@PathVariable String categoryName) {
        List<ArticleDto> articles = articleService.getArticlesByCategoryName(categoryName);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }


}