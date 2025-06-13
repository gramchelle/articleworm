package iau.articleworm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reactions")
@Getter
@Setter
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reaction_id")
    private Integer id;

    // Veritabanında tutulacak reaction type kodu (örneğin: 1 = LIKE, 2 = DISLIKE)
    @Column(name = "reaction_type", nullable = false)
    private short reactionTypeCode;

    // Enum olarak uygulama içinde kullanılacak (veritabanına yazılmaz)
    @Transient
    private ReactionType type;

    @PostLoad
    public void mapCodeToType() {
        this.type = ReactionType.fromValue(this.reactionTypeCode);
    }

    @PrePersist
    @PreUpdate
    public void mapTypeToCode() {
        if (this.type != null) {
            this.reactionTypeCode = this.type.getValue();
        }
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", nullable = false)
    //@JsonIgnore // Eğer Reaction üzerinden Article serialization'ında sonsuz döngü oluyorsa açabiliriz
    private Article article;
}
