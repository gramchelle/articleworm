package iau.articleworm.dto.Auth;

import lombok.Data;

@Data
public class LoginRequestDto {
    public String username;
    public String password;
}
