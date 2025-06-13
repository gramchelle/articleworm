package iau.articleworm.dto.Reaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionResponseByArticleDto {
    private Integer reactionId;
    private String reactionType;
    private UserSummaryDto user;
}
