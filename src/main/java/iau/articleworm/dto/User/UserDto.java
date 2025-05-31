package iau.articleworm.dto.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDto {

    private final Integer userId;
    private final String username;
    private final String email;
    private final String role;

}
