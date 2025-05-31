package iau.articleworm.repository;

import iau.articleworm.model.Follower;
import iau.articleworm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    List<Follower> findByUser(User user); // Takipçileri getirir
    List<Follower> findByFollowerUser(User follower); // Takip ettikleri

    Optional<Follower> findByUserAndFollowerUser(User user, User follower); // Takip ediyor mu?
}
