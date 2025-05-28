package iau.articleworm.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import iau.articleworm.repository.ArticleRepository;

@Service
@RequiredArgsConstructor // Constructor injection for final fields
public class ArticleManager{
    
    private final ArticleRepository articleRepository;

    @Qualifier("modelMapper")
    private final ModelMapper modelMapper;

    public boolean saveArticle(ArticleSaveRequestDto articleSaveRequestDto){
        Article article = modelMapper.map(articleSaveRequestDto, Article.class);
        articleRepository.save(article);
        return true;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }  

    public Article getArticleByName(String name) {
        return articleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with name: " + name));
    }



}
