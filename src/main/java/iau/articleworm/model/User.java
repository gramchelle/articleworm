package iau.articleworm.entities.concretes;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "passwordhash", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role_name", nullable = false)
    private String role;

    @Column(name = "created_at", nullable = false)
    private String createdAt; // Kullanıcının oluşturulma tarihi

    @OneToMany(mappedBy = "user")
    private Set<Follower> followers; // Kullanıcının takipçileri

    @OneToMany(mappedBy = "followerUser")
    private Set<Follower> following; // Kullanıcının takip ettikleri

    @ManyToMany(mappedBy = "users")
    private Set<Notification> notifications;

}
