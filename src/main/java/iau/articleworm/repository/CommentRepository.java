package iau.articleworm.entities.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import iau.articleworm.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
