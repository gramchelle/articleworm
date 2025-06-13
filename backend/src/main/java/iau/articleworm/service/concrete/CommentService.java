package iau.articleworm.service.concrete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iau.articleworm.model.Article;
import iau.articleworm.model.Comment;
import iau.articleworm.repository.CommentRepository;
import iau.articleworm.service.abstracts.ICommentService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByArticle(Article article) {
        return commentRepository.findByArticle(article);
    }

    @Override
    public List<Comment> findByArticleId(Integer articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    public void deleteComment(Integer commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new EntityNotFoundException("Comment not found with id: " + commentId);
        }
        commentRepository.deleteById(commentId);
    }
}
