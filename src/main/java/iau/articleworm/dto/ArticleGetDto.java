package iau.articleworm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleGetDto{
    
    private String title;
    private String content;
    private String author;
    private String category;
}