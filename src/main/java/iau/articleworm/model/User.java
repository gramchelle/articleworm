package iau.articleworm.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name = "users")
public class User /*implements UserDetails*/{

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
    private LocalDateTime createdAt; // Kullanıcının oluşturulma tarihi

    @OneToMany(mappedBy = "user")
    //@JsonIgnore
    private Set<Follower> followers; // Kullanıcının takipçileri

    @OneToMany(mappedBy = "followerUser")
    //@JsonIgnore
    private Set<Follower> following; // Kullanıcının takip ettikleri

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIgnore
    //@JsonManagedReference
    private List<Notification> notifications;
/*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }
*/
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    //@Transactional
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Article> articles;

    public Set<Article> getUserArticles() {
        return this.articles;
}

}
