package iau.articleworm.service.concrete;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import iau.articleworm.dto.User.*;
import iau.articleworm.model.*;
import iau.articleworm.repository.FollowerRepository;
import iau.articleworm.repository.UserRepository;
import iau.articleworm.service.abstracts.IUserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    
    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;
    private final ModelMapper modelMapper; // ModelMapper kullanarak DTO'ları User'a çeviriyoruz

    @Override
    public User createUser(UserSaveDto userSaveDto) {
        if (userRepository.findByEmail(userSaveDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists.");
        } else if (userRepository.findByUsername(userSaveDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User with this username already exists.");
        }

        if (userSaveDto.getEmail() == null || userSaveDto.getUsername() == null || userSaveDto.getPassword() == null) {
            throw new IllegalArgumentException("Email, username, and password must not be null.");
        }
        User user = new User();
        user.setUsername(userSaveDto.getUsername());
        user.setEmail(userSaveDto.getEmail());
        user.setPassword(userSaveDto.getPassword());
        user.setRole(userSaveDto.getRole()); // varsa rol de

        return userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new IllegalArgumentException("No users found.");
        }
        return users.stream()
                .map(user -> new UserDto(user.getUserId(), user.getUsername(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public UserByIdDto getUserById(Integer user_id) {
        if (user_id == null) {
            throw new IllegalArgumentException("User ID must not be null.");
        }
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + user_id + " does not exist."));

        return modelMapper.map(user, UserByIdDto.class);
    }

    @Override
    public UserByIdDto getUserByName(String username) {
        if (username.isEmpty() || username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID must not be null.");
        }
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + username + " does not exist."));

        return modelMapper.map(user, UserByIdDto.class);
    }

    @Override
    public boolean deleteUser(Integer user_id) {
        // Check if the user ID is not null
        if (user_id == null) {
            throw new IllegalArgumentException("User ID must not be null.");
        }
        // Check if user exists
        if (!userRepository.existsById(user_id)) {
            throw new IllegalArgumentException("User with ID " + user_id + " does not exist.");
        }
        // TODO: Check if user has followers and delete them
        // TODO: Check if user has articles and delete them

        // Delete the user
        try{
            userRepository.deleteById(user_id);  
            return true;
        } catch(Exception e) {
            throw new RuntimeException("Error occurred while deleting user with ID " + user_id + ": " + e.getMessage());
        }
    }

    @Override
    public User updateUser(Integer user_id, UserUpdateDto userUpdateDto) {
        if (user_id == null || userUpdateDto == null) {
            throw new IllegalArgumentException("User ID and update data must not be null.");
        }
        
        User existingUser = userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + user_id + " does not exist."));
        
        if (userUpdateDto.getRole() != null) {
            existingUser.setEmail(userUpdateDto.getRole());
        }
        if (userUpdateDto.getUsername() != null) {
            existingUser.setUsername(userUpdateDto.getUsername());
        }
        if (userUpdateDto.getPassword() != null) {
            existingUser.setPassword(userUpdateDto.getPassword());
        }
        
        return userRepository.save(existingUser);
    }
/*
    public List<User> getFollowers(Long user_id) {
        if (user_id == null) {
            throw new IllegalArgumentException("User ID must not be null.");
        }
        return followerRepository.findFollowersByUserId(user_id);
    }

    public List<User> getFollowing(Long user_id) {
        if (user_id == null) {
            throw new IllegalArgumentException("User ID must not be null.");
        }
        return followerRepository.findFollowingByUserId(user_id);
    }

    public void followUser(Long user_id, Long follower_id) {
        if (user_id == null || follower_id == null) {
            throw new IllegalArgumentException("User ID and follower ID must not be null.");
        }
        if (!userRepository.existsById(user_id)) {
            throw new IllegalArgumentException("User with ID " + user_id + " does not exist.");
        }
        if (!userRepository.existsById(follower_id)) {
            throw new IllegalArgumentException("Follower with ID " + follower_id + " does not exist.");
        }
        followerRepository.followUser(user_id, follower_id);
    }

    public void unfollowUser(Long user_id, Long follower_id) {
        if (user_id == null || follower_id == null) {
            throw new IllegalArgumentException("User ID and follower ID must not be null.");
        }
        if (!userRepository.existsById(user_id)) {
            throw new IllegalArgumentException("User with ID " + user_id + " does not exist.");
        }
        if (!userRepository.existsById(follower_id)) {
            throw new IllegalArgumentException("Follower with ID " + follower_id + " does not exist.");
        }
        followerRepository.unfollowUser(user_id, follower_id);
    }

    public int getFollowersCount(Long user_id) {
        if (user_id == null) {
            throw new IllegalArgumentException("User ID must not be null.");
        }
        return followerRepository.countFollowersByUserId(user_id);
    }

    public int getFollowingCount(Long user_id) {
        if (user_id == null) {
            throw new IllegalArgumentException("User ID must not be null.");
        }
        return followerRepository.countFollowingByUserId(user_id);
    }
*/
}
