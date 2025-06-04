package iau.articleworm.dto.Reaction;

import iau.articleworm.model.ReactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@RequiredArgsConstructor
//@NoArgsConstructor
public class ReactionResponseDto {
    private Integer id;
    private short reactionTypeCode;
    private ReactionType type;
    private Integer userId;
    private String userName;  // Eğer istersen user bilgisinden temel alanlar ekleyebilirsin
    private Integer articleId;

    public ReactionResponseDto() {
    }

    public ReactionResponseDto(Integer id, short reactionTypeCode, ReactionType type, Integer userId, String userName, Integer articleId) {
        this.id = id;
        this.reactionTypeCode = reactionTypeCode;
        this.type = type;
        this.userId = userId;
        this.userName = userName;
        this.articleId = articleId;
    }

}
