package iau.articleworm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iau.articleworm.dto.Notification.CreateNotificationRequest;
import iau.articleworm.dto.Notification.NotificationRequestDto;
import iau.articleworm.service.concrete.NotificationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<String> createNotification(@RequestBody CreateNotificationRequest request) {
        notificationService.createNotification(
                request.getMessage(),
                request.getType(),
                request.getUserId(),
                request.getArticleId()
        );
        return ResponseEntity.ok("Notification created");
    }

    @GetMapping("/unread/{userId}")
    public ResponseEntity<List<NotificationRequestDto>> getUnread(@PathVariable Integer userId) {
        return ResponseEntity.ok(notificationService.getUnreadNotifications(userId));
    }

    @PostMapping("/read/{notificationId}")
    public ResponseEntity<String> markRead(@PathVariable Integer notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok("Notification marked as read");
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<NotificationRequestDto>> getAllNotifications(@PathVariable Integer userId) {
        return ResponseEntity.ok(notificationService.getAllNotifications(userId));
    }

}