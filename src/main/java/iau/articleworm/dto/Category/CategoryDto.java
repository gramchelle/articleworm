package iau.articleworm.dto.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private String categoryName;
    private String description;

    public CategoryDto() {
    }

    public CategoryDto(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }
}
