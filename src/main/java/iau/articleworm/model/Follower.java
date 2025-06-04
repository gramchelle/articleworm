package iau.articleworm.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follower_id")
    private Integer id;

    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_user_id", referencedColumnName = "user_id")
    private User followerUser;

}