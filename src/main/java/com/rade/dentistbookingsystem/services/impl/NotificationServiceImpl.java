package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.domain.Discount;
import com.rade.dentistbookingsystem.domain.DiscountService;
import com.rade.dentistbookingsystem.domain.Notification;
import com.rade.dentistbookingsystem.repository.NotificationRepo;
import com.rade.dentistbookingsystem.services.NotificationService;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class NotificationServiceImpl implements NotificationService {
    NotificationRepo notificationRepo;
    @Override
    public Notification newDiscount(Discount discount) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int status = 0;
        String description = "Khuyến mãi mới" + discount.getName() + " trong thời gian giới hạn! " +
                "Bắt đầu từ " + sdf.format(discount.getStartDate()) + " đến " + sdf.format(discount.getEndDate()) + "! " +
                "Ưu đãi " + discount.getPercentage() + "% áp dụng cho dịch vụ: ";
        for (DiscountService discountService : discount.getDiscountServiceSet()) {
            description += discountService.getService().getName() + ", ";
        }
        description.substring(0, description.length() - 2);
        description += ". Chi tiết khuyến mãi: " + discount.getDescription();
        return addNotificationtoAllUser(description);
    }
    public Notification addNotificationtoAllUser(String description){
        return notificationRepo.save(new Notification(
                null,description,new Date()

        ));

    }

//    public Notification remindingAppointment(String phone){
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        int status = 2;
//        String description = "Nhắc bạn: bạn có lịch hẹn vào ";
//    }
}
