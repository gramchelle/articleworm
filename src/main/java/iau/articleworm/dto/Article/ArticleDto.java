package iau.articleworm.dto.Article;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleDto {

    private Integer id;
    private String title;
    private String content;
    private String category;
    private Integer authorId;
    private String authorName;
    private List<String> reactions;

}
