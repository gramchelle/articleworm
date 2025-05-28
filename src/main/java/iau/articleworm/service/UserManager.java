package iau.articleworm.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iau.articleworm.service.UserService;
import iau.articleworm.repository.UserRepository;
import iau.articleworm.model.User;

@Service
public class UserManager {

    private final UserRepository userDao;

    @Autowired
    public UserManager(UserRepository userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return this.userDao.findAll();
    }

    

}
