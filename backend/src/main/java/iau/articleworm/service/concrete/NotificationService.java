package iau.articleworm.service.concrete;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iau.articleworm.dto.Notification.NotificationRequestDto;
import iau.articleworm.model.Notification;
import iau.articleworm.model.User;
import iau.articleworm.repository.NotificationRepository;
import iau.articleworm.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public void createNotification(String message, String type, Integer userId, Integer articleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setNotificationMessage(message);
        notification.setNotificationType(type);
        notification.setArticleId(articleId);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);

        notificationRepository.save(notification);
    }

    @Transactional
    public void markAsRead(Integer notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        notificationRepository.save(notification);
    }

    public List<NotificationRequestDto> getUnreadNotifications(Integer userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    List<Notification> notifications = notificationRepository.findByUserAndIsReadFalse(user);

    return notifications.stream()
            .map(n -> new NotificationRequestDto(
                    n.getNotificationId(),
                    n.getNotificationMessage(),
                    n.getNotificationType(),
                    n.isRead(),
                    n.getUser().getUserId(),
                    n.getArticleId()
            ))
            .toList();
        }

    public List<NotificationRequestDto> getAllNotifications(Integer userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    List<Notification> notifications = notificationRepository.findByUser(user);

    return notifications.stream()
            .map(n -> new NotificationRequestDto(
                    n.getNotificationId(),
                    n.getNotificationMessage(),
                    n.getNotificationType(),
                    n.isRead(),
                    n.getUser().getUserId(),
                    n.getArticleId()
            ))
            .toList();
}
}