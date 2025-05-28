package iau.articleworm.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iau.articleworm.service.NotificationService;
import iau.articleworm.repository.NotificationRepository;
import iau.articleworm.model.Notification;

@Service
public class NotificationManager{

    private NotificationRepository notificationDao;
    
    @Autowired
    public NotificationManager(NotificationRepository notificationDao){
        this.notificationDao = notificationDao;
    }

}
