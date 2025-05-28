package iau.articleworm.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iau.articleworm.business.abstracts.ArticleService;
import iau.articleworm.entities.concretes.Article;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {

    private ArticleService articleService;

    @Autowired // bizim yerimize ArticleAService'i bulup newliyor -> constructor injection
    // constructor injection -> DI (Dependency Injection) -> Spring IoC (Inversion of Control) Container
    // Autowired, ArticleService'ı kimin implemente ettiğini bulup, onu new'liyor.
    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/getall")
    public List<Article> getAll(){
        return this.articleService.getAllArticles();
    }

    



}



//https://www.articleworm.com/username/articles/1
//https://www.articleworm.com/home/articles