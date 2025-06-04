package iau.articleworm.dto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserByIdDto {

    private Integer userId;
    private String username;
    private String email;
    private String role;

}
