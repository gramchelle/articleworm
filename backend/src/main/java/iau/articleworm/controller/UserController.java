package iau.articleworm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import iau.articleworm.dto.User.*;
import iau.articleworm.dto.Article.ArticleDto;
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
    public ResponseEntity<User> updateUser(@PathVariable Integer user_id, @RequestBody UserUpdateDto userUpdateDto) {
        User updatedUser = userService.updateUser(user_id, userUpdateDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<UserDto>> getFollowers(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.getFollowers(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<UserDto>> getFollowing(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.getFollowing(userId), HttpStatus.OK);
    }


    @PostMapping("/{user_id}/follow/{follower_id}")
    public void followUser(@PathVariable Integer user_id, @PathVariable Integer follower_id) {
        userService.followUser(user_id, follower_id);
    }
/* 
    @DeleteMapping("/{user_id}/unfollow/{follower_id}")
    public void unfollowUser(@PathVariable Integer user_id, @PathVariable Integer follower_id) {
        userService.unfollowUser(user_id, follower_id);
    }
 */

    @GetMapping("/{user_id}/followersCount")
    public ResponseEntity<Integer> getFollowersCount(@PathVariable Integer user_id) {
        return new ResponseEntity<>(userService.getFollowersCount(user_id), HttpStatus.OK);
    }

    @GetMapping("/{user_id}/followingCount")
    public ResponseEntity<Integer> getFollowingCount(@PathVariable Integer user_id) {
        return new ResponseEntity<>(userService.getFollowingCount(user_id), HttpStatus.OK);
    }

    @GetMapping("/{user_id}/articles")
    public ResponseEntity<List<ArticleDto>> getUserArticles(@PathVariable Integer user_id) {
        List<ArticleDto> articles = userService.getUserArticles(user_id);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
 
}
