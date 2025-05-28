package iau.articleworm.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iau.articleworm.business.abstracts.NotificationService;
import iau.articleworm.entities.abstracts.NotificationDao;
import iau.articleworm.entities.concretes.Notification;

@Service
public class NotificationManager implements NotificationService{

    private NotificationDao notificationDao;
    
    @Autowired
    public NotificationManager(NotificationDao notificationDao){
        this.notificationDao = notificationDao;
    }

    @Override
    public List<Notification> getAllNotificationsByUserId(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllNotificationsByUserId'");
    }

    @Override
    public List<Notification> getAllNotificationsByArticleId(int articleId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllNotificationsByArticleId'");
    }

}
