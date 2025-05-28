package iau.articleworm.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iau.articleworm.business.abstracts.UserService;
import iau.articleworm.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UserService userService;

    @Autowired // bizim yerimize ArticleAService'i bulup newliyor -> constructor injection
    // constructor injection -> DI (Dependency Injection) -> Spring IoC (Inversion of Control) Container
    // Autowired, ArticleService'ı kimin implemente ettiğini bulup, onu new'liyor.
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getall")
    public List<User> getAll() {
        return this.userService.getAllUsers();
    }

}
