package iau.articleworm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iau.articleworm.model.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

}
