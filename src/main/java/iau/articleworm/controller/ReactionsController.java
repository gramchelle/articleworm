package iau.articleworm.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iau.articleworm.business.abstracts.ReactionService;
import iau.articleworm.entities.concretes.Reaction;

@RestController
@RequestMapping("/api/reactions")
public class ReactionsController {

    private ReactionService reactionService; 

    @Autowired
    public ReactionsController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @GetMapping("/getall")
    public List<Reaction> getAllReactions() {
        return this.reactionService.getAllReactions();
    }

/*    @GetMapping("/reactions")
    public List<Reaction> getReactions() {
        return reactionService.getAllReactions()
            .stream()
            .map(reaction -> new Reaction(
                reaction.getId(),
                reaction.getReactionTypeById().name()
            ))
            .toList();
    }
*/

}
