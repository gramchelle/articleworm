package iau.articleworm.dto.Notification;

import lombok.Data;

@Data
public class CreateNotificationRequest {
    private String message;
    private String type;
    private Integer userId;
    private Integer articleId;
}