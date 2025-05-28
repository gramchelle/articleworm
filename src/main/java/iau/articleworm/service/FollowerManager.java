package iau.articleworm.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iau.articleworm.service.FollowerService;
import iau.articleworm.repository.FollowerRepository;
import iau.articleworm.model.Follower;


@Service
public class FollowerManager{

    private FollowerRepository followerDao;

    @Autowired
    public FollowerManager(FollowerRepository followerDao){
        this.followerDao = followerDao;
    }

    public List<Follower> getAllFollowersByUserId(int userId) {
        
    }

}
