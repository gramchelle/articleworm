package iau.articleworm.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reactions")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reaction_id")
    private Integer id;

    // Veritabanına kaydedilecek değer (SMALLINT ile eşleşir)
    @Column(name = "reaction_type")
    private short reactionTypeCode;

    // Enum olarak anlamlı erişim için (DB'de tutulmaz)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", nullable = false)
    private Article article;
}
