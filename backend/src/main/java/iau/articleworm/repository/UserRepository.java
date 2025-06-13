package iau.articleworm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import iau.articleworm.model.Article;
import iau.articleworm.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.userId = ?1")
    Optional<User> findById(Integer id);

    @Transactional // Ensure that the method is transactional, because it modifies the database
    @Modifying
    @Query("DELETE FROM User u WHERE u.username = ?1")
    void deleteByUsername(String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = ?1")
    void deleteById(Integer id);

    @Query("SELECT u FROM User u WHERE u.id = :userId")
    Optional<User> findByIdWithArticles(@Param("userId") Integer userId);

}
