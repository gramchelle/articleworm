package iau.articleworm.dto.Article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleSaveDto{
    
    private String title;
    private String content;
    private Integer author_id; 
    private Integer category_id; 
}