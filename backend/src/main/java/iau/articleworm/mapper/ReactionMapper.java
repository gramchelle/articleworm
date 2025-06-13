package iau.articleworm.mapper;

import iau.articleworm.dto.Reaction.ReactionResponseDto;
import iau.articleworm.model.Reaction;

public class ReactionMapper {

    public static ReactionResponseDto toDto(Reaction reaction) {
        ReactionResponseDto dto = new ReactionResponseDto();
        dto.setId(reaction.getId());
        dto.setReactionTypeCode(reaction.getReactionTypeCode());
        dto.setType(reaction.getType());
        dto.setUserId(reaction.getUser().getUserId());
        dto.setUserName(reaction.getUser().getUsername());
        dto.setArticleId(reaction.getArticle().getArticle_id());
        return dto;
    }
}
