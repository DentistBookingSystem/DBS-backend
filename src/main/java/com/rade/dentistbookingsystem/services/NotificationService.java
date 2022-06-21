package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.componentform.PhoneAndPage;
import com.rade.dentistbookingsystem.domain.Discount;
import com.rade.dentistbookingsystem.domain.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NotificationService {

    <S extends Notification> S save(S entity);

    Notification newDiscount(Discount discount);

    List<Notification> findByAccountId(Integer accountId, Pageable pageable);

    List<Notification> findByAccountId(PhoneAndPage phoneAndPage);

    Notification findDuplicateDescriptionByAccountId(Integer accountId, String description);

    Notification findDuplicateDescription(Notification notification);

    void createRemindNotificationIfNeeded(String phone);
}
