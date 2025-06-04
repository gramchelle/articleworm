package iau.articleworm.controller;

import iau.articleworm.dto.Notification.CreateNotificationRequest;
import iau.articleworm.model.Notification;
import iau.articleworm.service.concrete.NotificationService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Notification>> getUnread(@PathVariable Integer userId) {
        return ResponseEntity.ok(notificationService.getUnreadNotifications(userId));
    }

    @PostMapping("/read/{notificationId}")
    public ResponseEntity<String> markRead(@PathVariable Integer notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok("Notification marked as read");
    }

}
