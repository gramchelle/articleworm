package iau.articleworm.controller;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iau.articleworm.dto.Comment.CommentDto;
import iau.articleworm.dto.Comment.CommentRequestDto;
import iau.articleworm.model.Article;
import iau.articleworm.model.Comment;
import iau.articleworm.model.User;
import iau.articleworm.repository.ArticleRepository;
import iau.articleworm.repository.UserRepository;
import iau.articleworm.service.concrete.CommentService;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/{articleId}/add/{userId}")
    public ResponseEntity<?> addComment(@PathVariable Integer articleId,
                                        @PathVariable Integer userId,
                                        @RequestBody CommentRequestDto commentRequestDTO) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Article> articleOpt = articleRepository.findById(articleId);

        if (userOpt.isPresent() && articleOpt.isPresent()) {
            Comment comment = new Comment();
            comment.setUserId(userOpt.get());
            comment.setArticle(articleOpt.get());
            comment.setContent(commentRequestDTO.getContent());

            Comment saved = commentService.addComment(comment);
            CommentDto responseDto = new CommentDto(saved);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Article not found");
    }

    @GetMapping("/article/{id}")
    public List<CommentDto> getCommentsByArticle(@PathVariable Integer id) {
        List<Comment> comments = commentService.findByArticleId(id);
        return comments.stream()
                    .map(CommentDto::new)
                    .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Comment deleted successfully.");
    }
}
