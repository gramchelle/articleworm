package iau.articleworm.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleUpdateDto {

    private String title;
    private String content;
    private String category;
}
