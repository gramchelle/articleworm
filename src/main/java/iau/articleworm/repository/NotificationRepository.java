package iau.articleworm.repository;

import iau.articleworm.model.Notification;
import iau.articleworm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByUserAndIsReadFalse(User user);
    List<Notification> findByUser(User user);
    
}
