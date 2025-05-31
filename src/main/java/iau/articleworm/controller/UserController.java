package iau.articleworm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import iau.articleworm.dto.User.*;
import iau.articleworm.model.*;
import iau.articleworm.service.concrete.UserService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserByIdDto> getUserById(@PathVariable Integer id) {
        UserByIdDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/getByName/{username}")  
    public UserByIdDto getUserByName(@PathVariable String username) {
        return userService.getUserByName(username);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody UserSaveDto user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer user_id) {
        boolean deleted = userService.deleteUser(user_id);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User could not be deleted.");
        }
    }

    @PutMapping("/{user_id}/update")
    public User updateUser(@PathVariable Integer user_id, @RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(user_id, userUpdateDto);
    }

/*
    @GetMapping("/followers/{user_id}")
    public List<User> getFollowers(@PathVariable Long user_id) {
        return userService.getFollowers(user_id);
    }

    @GetMapping("/following/{user_id}")
    public List<User> getFollowing(@PathVariable Long user_id) {
        return userService.getFollowing(user_id);
    }

    @PostMapping("/{user_id}/follow/{follower_id}")
    public void followUser(@PathVariable Long user_id, @PathVariable Long follower_id) {
        userService.followUser(user_id, follower_id);
    }

    @DeleteMapping("/{user_id}/unfollow/{follower_id}")
    public void unfollowUser(@PathVariable Long user_id, @PathVariable Long follower_id) {
        userService.unfollowUser(user_id, follower_id);
    }

    @GetMapping("/followersCount/{user_id}")
    public int getFollowersCount(@PathVariable Long user_id) {
        return userService.getFollowersCount(user_id);
    }

    @GetMapping("/followingCount/{user_id}")
    public int getFollowingCount(@PathVariable Long user_id) {
        return userService.getFollowingCount(user_id);
    } */

}
