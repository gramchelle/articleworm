package iau.articleworm.repository;

import iau.articleworm.model.Follower;
import iau.articleworm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowerRepository extends JpaRepository<Follower, Integer> {

    List<Follower> findByUser(User user); // kullanıcıyı takip edenler
    List<Follower> findByFollowerUser(User followerUser); // kullanıcının takip ettikleri

    Optional<Follower> findByUserAndFollowerUser(User user, User followerUser); // kullanıcı X kişisini takip ediyor mu?

    @Query("SELECT f FROM Follower f WHERE f.followerUser.id = :followerUser")
    int countFollowingByUserId(@Param("followerUser") Integer followerUser); // YANLIŞ ÇALIŞIYOR

    @Query("SELECT COUNT(f) FROM Follower f WHERE f.user.id = :userId")
    int countFollowersByUserId(@Param("userId") Integer userId);
}
