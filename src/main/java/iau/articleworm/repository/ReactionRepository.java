package iau.articleworm.entities.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import iau.articleworm.entities.concretes.Reaction;

public interface ReactionDao extends JpaRepository<Reaction, Integer> {

}
