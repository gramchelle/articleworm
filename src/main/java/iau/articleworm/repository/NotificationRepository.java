package iau.articleworm.entities.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import iau.articleworm.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
