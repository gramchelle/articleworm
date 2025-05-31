package iau.articleworm.controller;

import iau.articleworm.model.Follower;
import iau.articleworm.service.concrete.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowerController {

    private final FollowerService followerService;

    @PostMapping("/{userId}/follow/{followerId}")
    public String follow(@PathVariable Integer userId, @PathVariable Integer followerId) {
        followerService.followUser(userId, followerId);
        return "Takip edildi.";
    }

    @DeleteMapping("/{userId}/unfollow/{followerId}")
    public String unfollow(@PathVariable Integer userId, @PathVariable Integer followerId) {
        followerService.unfollowUser(userId, followerId);
        return "Takipten çıkıldı.";
    }

    @GetMapping("/{userId}/followers")
    public List<Follower> getFollowers(@PathVariable Integer userId) {
        return followerService.getFollowers(userId);
    }

    @GetMapping("/{userId}/following")
    public List<Follower> getFollowing(@PathVariable Integer userId) {
        return followerService.getFollowing(userId);
    }
}
