package iau.articleworm.dto.Comment;

import iau.articleworm.model.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Integer id;
    private String content;
    private String userName;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userName = comment.getUserId().getUsername();
    }
}
