package iau.articleworm.entities.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import iau.articleworm.model.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

}
