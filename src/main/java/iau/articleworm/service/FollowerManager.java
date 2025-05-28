package iau.articleworm.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iau.articleworm.business.abstracts.FollowerService;
import iau.articleworm.entities.abstracts.FollowerDao;
import iau.articleworm.entities.concretes.Follower;


@Service
public class FollowerManager implements FollowerService{

    private FollowerDao followerDao;

    @Autowired
    public FollowerManager(FollowerDao followerDao){
        this.followerDao = followerDao;
    }

    @Override
    public List<Follower> getAllFollowersByUserId(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllFollowersByUserId'");
    }

}
