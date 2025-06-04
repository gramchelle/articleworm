package iau.articleworm.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private int notificationId;

    // @ManyToMany ile tekil ilişkiyi tanımlarken doğru kullanımı uygulayalım.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "notification_user", // Ortak tablo ismi
        joinColumns = @JoinColumn(name = "notification_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @Column(name = "notification_message")
    private String notificationMessage; // like, comment, follow

    @Column(name = "is_read")
    private boolean isRead;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
