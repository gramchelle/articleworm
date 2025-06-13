package iau.articleworm.service.abstracts;

import java.util.List;

import iau.articleworm.dto.User.*;
import iau.articleworm.model.User;
import iau.articleworm.dto.Article.ArticleDto;

public interface IUserService {

    List<UserDto> getAllUsers();
    UserByIdDto getUserById(Integer user_id);
    UserByIdDto getUserByName(String username);
    User createUser(UserSaveDto user);
    User updateUser(Integer user_id, UserUpdateDto userUpdateDto);
    boolean deleteUser(Integer user_id);
    List<ArticleDto> getUserArticles(Integer user_id);
}
