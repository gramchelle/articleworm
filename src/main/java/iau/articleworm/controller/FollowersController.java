package iau.articleworm.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iau.articleworm.business.abstracts.FollowerService;
import iau.articleworm.entities.concretes.Follower;

@RestController
@RequestMapping("/api/followers")
public class FollowersController {

    private FollowerService followerService;

    @Autowired
    public FollowersController(FollowerService followerService) {
        this.followerService = followerService;
    }

    @GetMapping("/getall")
    public List<Follower> getAllFollowersByUserId(int userId) {
        return this.followerService.getAllFollowersByUserId(userId);
    }


}
