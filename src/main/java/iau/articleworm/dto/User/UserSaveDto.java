package iau.articleworm.dto.User;

import lombok.Setter;

import java.util.Set;

import iau.articleworm.model.Follower;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Getter
@Setter
public class UserSaveDto {

    private String username;
    private String email;
    private String password;
    private String role = "READER"; // default role

}
