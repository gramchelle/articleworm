package iau.articleworm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iau.articleworm.model.Article;
import iau.articleworm.model.Reaction;
import iau.articleworm.model.User;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

    Optional<Reaction> findByUserAndArticle(User user, Article article);
/* 
    Optional<Reaction> findByUserAndArticle(User user, Article article);
    List<Reaction> findByArticleId(Integer articleId);
    // alan isimleri entity'deki gibi olmalı
    List<Reaction> findByArticleArticle_id(Integer articleId);
 */
    @Query("SELECT r FROM Reaction r WHERE r.article.id = :articleId")
    List<Reaction> findByArticleId(@Param("articleId") Integer articleId);


}
