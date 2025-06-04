package iau.articleworm.dto.Article;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleDto {

    private Long id;
    private String title;
    private String content;
    private String category;
    private List<String> reactions;

}
