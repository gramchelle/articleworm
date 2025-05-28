package iau.articleworm.entities.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import iau.articleworm.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer> {
    // User entity'sine özel sorgular buraya yazılabilir.
    // Örneğin: List<User> findByUsername(String username);
    // @Query("SELECT u FROM User u WHERE u.username = ?1")
    // List<User> findByUsername(String username);

}
