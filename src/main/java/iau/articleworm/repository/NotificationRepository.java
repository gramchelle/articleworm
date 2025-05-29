package iau.articleworm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iau.articleworm.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
