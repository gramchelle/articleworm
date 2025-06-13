package iau.articleworm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iau.articleworm.model.Article;
import iau.articleworm.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByArticle(Article article_id);

    @Query("SELECT c FROM Comment c WHERE c.article.article_id = :articleId")
    List<Comment> findByArticleId(@Param("articleId") Integer articleId);
    
    void deleteById(Integer id);
}

