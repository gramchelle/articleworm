package iau.articleworm.service.concrete;

import iau.articleworm.model.Follower;
import iau.articleworm.model.User;
import iau.articleworm.repository.FollowerRepository;
import iau.articleworm.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowerService {

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;

    public void followUser(Integer userId, Integer followerId) {
        if (userId.equals(followerId)) {
            throw new IllegalArgumentException("Kendi kendini takip edemezsin.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Takip edilecek kullanıcı bulunamadı"));
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Takip eden kullanıcı bulunamadı"));

        followerRepository.findByUserAndFollowerUser(user, follower)
                .ifPresent(f -> {
                    throw new RuntimeException("Zaten takip ediyorsun.");
                });

        Follower f = new Follower();
        f.setUser(user);
        f.setFollowerUser(follower);
        //f.setFollowedAt(LocalDateTime.now());
        followerRepository.save(f);
    }

    @Transactional
    public void unfollowUser(Integer userId, Integer followerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Takipten çıkarılacak kullanıcı bulunamadı"));
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Takip eden kullanıcı bulunamadı"));

        Follower f = followerRepository.findByUserAndFollowerUser(user, follower)
                .orElseThrow(() -> new RuntimeException("Bu kullanıcıyı takip etmiyorsun"));

        followerRepository.delete(f);
    }

    public List<Follower> getFollowers(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return followerRepository.findByUser(user);
    }

    public List<Follower> getFollowing(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return followerRepository.findByFollowerUser(user);
    }
}
