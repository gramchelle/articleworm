package iau.articleworm.dto.Reaction;

import iau.articleworm.model.ReactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionRequestDto {
    private ReactionType reactionType;
}
