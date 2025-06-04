package iau.articleworm.controller;

import iau.articleworm.dto.Reaction.ReactionResponseByArticleDto;
import iau.articleworm.dto.Reaction.ReactionResponseDto;
import iau.articleworm.model.Article;
import iau.articleworm.model.Reaction;
import iau.articleworm.model.User;
import iau.articleworm.model.ReactionType;
import iau.articleworm.service.concrete.ReactionService;
import iau.articleworm.repository.ArticleRepository;
import iau.articleworm.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reactions")
@RequiredArgsConstructor
public class ReactionController {
/* 
    private final ReactionService reactionService;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @PostMapping("/{reactionTypeCode}/add/{userId}/{articleId}")
    public ResponseEntity<?> addReaction(@PathVariable String reactionTypeCode,
                                        @PathVariable Integer userId,
                                        @PathVariable Integer articleId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Article> articleOpt = articleRepository.findById(articleId);

        if (userOpt.isEmpty() || articleOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User or Article not found");
        }

        ReactionType reactionType;
        try {
            reactionType = ReactionType.valueOf(reactionTypeCode.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid reaction type");
        }

        Reaction reaction = reactionService.addOrUpdateReaction(userOpt.get(), articleOpt.get(), reactionType);
        ReactionResponseDto dto = mapToDto(reaction);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    private ReactionResponseDto mapToDto(Reaction reaction) {
        if (reaction == null) {
            return null;
        }

        ReactionResponseDto dto = new ReactionResponseDto();
        dto.setId(reaction.getId());
        dto.setReactionTypeCode(reaction.getReactionTypeCode());
        dto.setType(reaction.getType());

        if (reaction.getUser() != null) {
            dto.setUserId(reaction.getUser().getUserId());
            dto.setUserName(reaction.getUser().getUsername());
        }

        if (reaction.getArticle() != null) {
            dto.setArticleId(reaction.getArticle().getArticle_id());
        }

        return dto;
    }
 */
    // ABOVE IS WORKING
/* 
    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<ReactionResponseByArticleDto>> getReactionsByArticle(@PathVariable Integer articleId) {
        List<ReactionResponseByArticleDto> reactions = reactionService.getReactionsByArticle(articleId);
        return new ResponseEntity<>(reactions, HttpStatus.OK);
    } */
}
