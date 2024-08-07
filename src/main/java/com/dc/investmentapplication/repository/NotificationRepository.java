package com.dc.investmentapplication.repository;

import com.dc.investmentapplication.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
