package iau.articleworm.entities.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import iau.articleworm.model.Follower;

public interface FollowerRepository extends JpaRepository<Follower, Integer> {
    

}
