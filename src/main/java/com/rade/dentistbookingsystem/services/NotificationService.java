package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.Discount;
import com.rade.dentistbookingsystem.domain.Notification;
import org.springframework.stereotype.Service;

public interface NotificationService {

    Notification newDiscount(Discount discount);
}
