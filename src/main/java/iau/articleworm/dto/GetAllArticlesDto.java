package iau.articleworm.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllArticles {
    private String title;
    private String author;
    private String category;
}
