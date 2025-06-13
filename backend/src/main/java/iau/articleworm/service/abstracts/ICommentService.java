package iau.articleworm.service.abstracts;

import java.util.List;

import iau.articleworm.model.Comment;

public interface ICommentService {
    List<Comment> findByArticleId(Integer articleId);
}
