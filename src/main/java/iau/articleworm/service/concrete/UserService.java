package iau.articleworm.service.concrete;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import iau.articleworm.dto.User.*;
import iau.articleworm.dto.Article.ArticleDto;
import iau.articleworm.model.*;
import iau.articleworm.repository.FollowerRepository;
import iau.articleworm.repository.UserRepository;
import iau.articleworm.service.abstracts.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    
    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;
    private final ModelMapper modelMapper; // ModelMapper kullanarak DTO'ları User'a çeviriyoruz
    private final ArticleService articleService;

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

    public List<UserDto> getFollowers(Integer userId) {
        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

        List<Follower> followers = followerRepository.findByUser(user);

        return followers.stream()
                .map(f -> new UserDto(
                        f.getFollowerUser().getUserId(),
                        f.getFollowerUser().getUsername(),
                        f.getFollowerUser().getEmail(),
                        f.getFollowerUser().getRole()
                ))
                .toList();
    }

    public List<UserDto> getFollowing(Integer userId) {
        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

        List<Follower> following = followerRepository.findByFollowerUser(user);

        return following.stream()
                .map(f -> new UserDto(
                        f.getUser().getUserId(),
                        f.getUser().getUsername(),
                        f.getUser().getEmail(),
                        f.getUser().getRole()
                ))
                .toList();
    }

    public void followUser(Integer userId, Integer followerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Takip edilen kullanıcı bulunamadı"));
        User followerUser = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Takip eden kullanıcı bulunamadı"));

        // Zaten takip ediyor mu kontrol et
        boolean alreadyFollowing = followerRepository.findByUserAndFollowerUser(user, followerUser).isPresent();
        if (alreadyFollowing) {
            throw new RuntimeException("Zaten takip ediliyor");
        }

        Follower follower = new Follower();
        follower.setUser(user); // takip edilen
        follower.setFollowerUser(followerUser); // takip eden

        followerRepository.save(follower);
    }
/* 
    public void unfollowUser(Integer userId, Integer followerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Takip edilen kullanıcı bulunamadı"));
        User followerUser = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Takip eden kullanıcı bulunamadı"));

        Follower follower = followerRepository.findByUserAndFollowerUser(user, followerUser)
                .orElseThrow(() -> new RuntimeException("Takip bilgisi bulunamadı"));

        followerRepository.delete(follower);
    } */

    public int getFollowersCount(Integer user_id) {
        if (user_id == null) {
            throw new IllegalArgumentException("User ID must not be null.");
        }
        return followerRepository.countFollowersByUserId(user_id);
    }

    public int getFollowingCount(Integer user_id) {
        if (user_id == null) {
            throw new IllegalArgumentException("User ID must not be null.");
        }
        return followerRepository.countFollowersByUserId(user_id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleDto> getUserArticles(Integer user_id) {
        User user = userRepository.findByIdWithArticles(user_id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + user_id + " does not exist."));

        return user.getUserArticles().stream()
                .map(articleService::convertToDto)
                .collect(Collectors.toList());
    }

    private ArticleDto convertToDto(Article article) {
        ArticleDto dto = new ArticleDto();
        dto.setId(article.getArticle_id().longValue());
        dto.setTitle(article.getTitle());
        dto.setContent(article.getContent());
        dto.setCategory(article.getCategory().getCategoryName());

        List<String> reactionTypes = article.getReactions()
            .stream()
            .map(reaction -> reaction.getType().toString())
            .collect(Collectors.toList());
        dto.setReactions(reactionTypes);

        return dto;
    }

}