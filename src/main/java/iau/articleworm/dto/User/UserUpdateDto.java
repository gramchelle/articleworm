package iau.articleworm.dto.User;

import java.util.Set;

import iau.articleworm.model.Follower;
import iau.articleworm.model.Notification;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {

    private String username;
    private String password;

    private String role;
    private Set<Follower> following;
    private Set<Follower> followers;
}
