package iau.articleworm.service.concrete;

import iau.articleworm.dto.Reaction.ReactionResponseByArticleDto;
import iau.articleworm.dto.Reaction.UserSummaryDto;
import iau.articleworm.model.Article;
import iau.articleworm.model.Reaction;
import iau.articleworm.model.ReactionType;
import iau.articleworm.model.User;
import iau.articleworm.repository.ReactionRepository;
import iau.articleworm.service.abstracts.IReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactionService implements IReactionService {

   /*  private final ReactionRepository reactionRepository;

    //@Override
    public Reaction addOrUpdateReaction(User user, Article article, ReactionType reactionType) {
        Optional<Reaction> existingReactionOpt = reactionRepository.findByUserAndArticle(user, article);

        Reaction reaction;
        if (existingReactionOpt.isPresent()) {
            reaction = existingReactionOpt.get();
            reaction.setType(reactionType);
        } else {
            reaction = new Reaction();
            reaction.setUser(user);
            reaction.setArticle(article);
            reaction.setType(reactionType);
        }

        return reactionRepository.save(reaction);
    }
 */

/*     @Override
    public List<ReactionResponseByArticleDto> getReactionsByArticle(Integer articleId) {
        List<Reaction> reactions = reactionRepository.findByArticleId(articleId);

        return reactions.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ReactionResponseByArticleDto mapToDto(Reaction reaction) {
        User user = reaction.getUser();

        UserSummaryDto userDTO = new UserSummaryDto();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());

        ReactionResponseByArticleDto dto = new ReactionResponseByArticleDto();
        dto.setReactionId(reaction.getId());
        dto.setReactionType(reaction.getType().name());
        dto.setUser(userDTO);

        return dto;
    } */
}
