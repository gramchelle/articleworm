package iau.articleworm.entities.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import iau.articleworm.entities.concretes.Notification;

public interface NotificationDao extends JpaRepository<Notification, Integer> {

}
