package iau.articleworm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import iau.articleworm.service.concrete.ArticleService;
import lombok.RequiredArgsConstructor;
import iau.articleworm.dto.ArticleDto;
import iau.articleworm.dto.ArticleSaveDto;
import iau.articleworm.model.Article;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    @GetMapping("/getAll")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping("/saveArticle")
    public ResponseEntity<Boolean> createArticle(@RequestBody ArticleSaveDto articleSaveDto) {
        Boolean articleSaved = articleService.saveArticle(articleSaveDto);
        return new ResponseEntity<>(articleSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article) {
        return articleService.updateArticle(id, article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
    

}