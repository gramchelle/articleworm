package iau.articleworm.dto.Notification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationRequestDto {
    private Integer notificationId;
    private String message;
    private String type;
    private boolean isRead;
    private Integer userId;
    private Integer articleId;
}