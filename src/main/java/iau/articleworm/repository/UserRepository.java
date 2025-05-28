package iau.articleworm.entities.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import iau.articleworm.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    // User entity'sine özel sorgular buraya yazılabilir.
    // Örneğin: List<User> findByUsername(String username);
    // @Query("SELECT u FROM User u WHERE u.username = ?1")
    // List<User> findByUsername(String username);

}
