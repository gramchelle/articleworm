package iau.articleworm.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleSaveDto{
    
    private String title;
    private String content;
    private String author;
    private String category;
}