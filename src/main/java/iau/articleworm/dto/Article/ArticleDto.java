package iau.articleworm.dto.Article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto{
    
    private Long id;
    private String title;
    private String content;
//    private Integer author_id;
    private String category;
}