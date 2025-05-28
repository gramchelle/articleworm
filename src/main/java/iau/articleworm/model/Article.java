package iau.articleworm.model;

import java.sql.Time;

import jakarta.persistence.*; //persist etmek -> datayı kalıcı hale getirmek
import lombok.Data;

@Data
@Entity //sen bir veri tabanı nesnesisin arkadaşım demek
@Table(name = "articles")
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Integer article_id;

    @Column(name= "title", nullable = false)
    private String title;
    
    @Column(name = "artice_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private Category category;

    @Column(name = "created_at")
    private Time createdAt;

    @Column(name = "updated_at")
    private Time updatedAt;

}
