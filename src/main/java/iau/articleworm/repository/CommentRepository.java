package iau.articleworm.entities.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import iau.articleworm.entities.concretes.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer> {

}
