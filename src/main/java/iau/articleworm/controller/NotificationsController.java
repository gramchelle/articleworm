package iau.articleworm.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iau.articleworm.service.NotificationService;
import iau.articleworm.model.Notification;

@RestController
@RequestMapping("/api/notifications")
public class NotificationsController {

    private NotificationService notificationService;

    @Autowired
    public NotificationsController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @GetMapping("/getall")
    public List<Notification> getAllNotificationsByUserId(int userId){
        return this.notificationService.getAllNotificationsByUserId(userId);
    }

}
