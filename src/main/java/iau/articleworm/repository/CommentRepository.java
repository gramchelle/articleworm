package iau.articleworm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iau.articleworm.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
