package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.componentform.PhoneAndPage;
import com.rade.dentistbookingsystem.domain.*;
import com.rade.dentistbookingsystem.repository.NotificationRepo;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.AppointmentService;
import com.rade.dentistbookingsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    NotificationRepo notificationRepo;

    public NotificationServiceImpl(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    @Autowired
    AccountService accountService;

    @Autowired
    AppointmentService appointmentService;
    @Override
    public <S extends Notification> S save(S entity) {
        return notificationRepo.save(entity);
    }

    @Override
    public Notification newDiscount(Discount discount){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int status = 0;
        String description = "Khuyến mãi mới" + discount.getName() + " trong thời gian giới hạn! " +
                "Bắt đầu từ " + sdf.format(discount.getStartDate()) + " đến " + sdf.format(discount.getEndDate()) + "! " +
                "Ưu đãi " + discount.getPercentage() + "% áp dụng cho dịch vụ: ";
        for(DiscountService discountService: discount.getDiscountServiceSet()){
            description += discountService.getService().getName() + ", ";
        }
        description.substring(0, description.length() - 2);
        description += ". Chi tiết khuyến mãi: " + discount.getDescription();
        Notification notification = new Notification(
                null,
                description,
                new Date()
        );
        return save(notification);
    }

    @Override
    public List<Notification> findByAccountId(Integer accountId, Pageable pageable) {
        return findByAccountId(accountId, pageable);
    }

    @Override
    public List<Notification> findByAccountId(PhoneAndPage phoneAndPage) {
        try{
            int accountId = accountService.findByPhone(phoneAndPage.getPhone()).getId();
            int page = phoneAndPage.getPage() - 1;
            Pageable pageable = PageRequest.of(page, 3, Sort.by("id").descending());
            return notificationRepo.findByAccountId(accountId, pageable);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Notification findDuplicateDescriptionByAccountId(Integer accountId, String description) {
        return notificationRepo.findDuplicateDescriptionByAccountId(accountId, description);
    }

    @Override
    public Notification findDuplicateDescription(Notification notification) {
        Integer accountId = notification.getAccount().getId();
        String description = notification.getDescription();
        return findDuplicateDescriptionByAccountId(accountId, description);
    }

    @Override
    public void createRemindNotificationIfNeeded(String phone){
        Account account = accountService.findByPhone(phone);
        if (account == null) return;
        Appointment appointment = appointmentService.findAppointmentByAccountIdInNext24h(account.getId());
        if(appointment != null){
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdfDate.format(appointment.getAppointmentDate());
            String time = appointment.getAppointmentTime().split("-")[0];
            String description = "Nhắc yêu: Bạn có lịch hẹn khám răng vào lúc " + time + " ngày " + date +
                    " tại trung tâm nha khoa RaDe " + appointment.getBranch().getName() + ". Nhớ đến đúng giờ nhé <3";
            Notification notification = new Notification(
                    account,
                    description,
                    new Date()
            );
            if(findDuplicateDescription(notification) == null) save(notification);
        }
    }

    @Override
    public void createNotificationForAbsent(Appointment appointment){
        Account account = appointment.getAccount();
        if (account == null) return;
        if(appointment != null){
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdfDate.format(appointment.getAppointmentDate());
            String time = appointment.getAppointmentTime().split("-")[0];
            String description = "Lịch hẹn khám răng vào lúc " + time + " ngày " + date +
                    " tại trung tâm nha khoa RaDe " + appointment.getBranch().getName() + " đã bị hủy do bạn không có mặt quá 15 phút.";
            Notification notification = new Notification(
                    account,
                    description,
                    new Date()
            );
            save(notification);
        }
    }

    @Override
    public void createNotificationForBannedByAbsent(Integer accountId){
        Account account = accountService.findId(accountId);
        if(account != null){
            String description = "Bạn đã bị liệt vào danh sách đen của trung tâm vì đã vắng liên tục quá 3 lịch hẹn. Hiện tại bạn sẽ chỉ còn có thể tham khảo các thông tin mà trung tâm cung cấp.";
            Notification notification = new Notification(
                    account,
                    description,
                    new Date()
            );
            save(notification);
        }
    }

    @Override
    public void createNotificationForBannedByFeedback(Integer accountId){
        Account account = accountService.findId(accountId);
        if(account != null){
            String description = "Bạn đã bị liệt vào danh sách đen của trung tâm vì đã phản hồi vi phạm, quấy rối quá 2 lần. Hiện tại bạn sẽ chỉ còn có thể tham khảo các thông tin mà trung tâm cung cấp.";
            Notification notification = new Notification(
                    account,
                    description,
                    new Date()
            );
            save(notification);
        }
    }

    @Override
    public void createNotificationForCancellingAppointment(Appointment appointment){
        Account account = appointment.getAccount();
        if (account == null) return;
        if(appointment != null){
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdfDate.format(appointment.getAppointmentDate());
            String time = appointment.getAppointmentTime().split("-")[0];
            String description = "Bạn đã hủy lịch hẹn khám răng vào lúc " + time + " ngày " + date +
                    " tại trung tâm nha khoa RaDe " + appointment.getBranch().getName() + ".";
            Notification notification = new Notification(
                    account,
                    description,
                    new Date()
            );
            save(notification);
        }
    }

    @Override
    public void createNotificationForUpdatingAppointment(Appointment appointment){
        if(appointment != null && appointment.getAccount() != null){
            String description = "Lịch hẹn sắp tới của bạn đã được cập nhật";
            Notification notification = new Notification(
                    appointment.getAccount(),
                    description,
                    new Date()
            );
            save(notification);
        }
    }

    @Override
    public void createNotificationForCancellingAppointmentFromAdmin(Appointment appointment, String description){
        Account account = appointment.getAccount();
        if (description == null) return;
        else description = description.trim();
        if (account == null) return;
        if(appointment != null){
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdfDate.format(appointment.getAppointmentDate());
            String time = appointment.getAppointmentTime().split("-")[0];
            description = "Lịch hẹn khám răng vào lúc " + time + " ngày " + date +
                    " tại trung tâm nha khoa RaDe " + appointment.getBranch().getName() + " đã bị hủy do: " + description +
                    ". Xin quý khách thông cảm vì sự bất tiện này.";
            Notification notification = new Notification(
                    account,
                    description,
                    new Date()
            );
            save(notification);
        }
    }

    @Override
    public void createNotificationForApprovingFeedbackFromAdmin(Feedback feedback){
        if(feedback != null && feedback.getAppointment() != null && feedback.getAppointment().getAccount() != null){
            Appointment appointment = feedback.getAppointment();
            Account account = feedback.getAppointment().getAccount();
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdfDate.format(appointment.getAppointmentDate());
            String time = appointment.getAppointmentTime().split("-")[0];
            String description = "Phản hồi cho lịch hẹn vào lúc " + time + " ngày " + date +
                    " tại trung tâm nha khoa RaDe " + appointment.getBranch().getName() + " đã được phê duyệt, xin cảm ơn quý khách đã để lại phản hồi tới trung tâm nha khoa RaDe";
            Notification notification = new Notification(
                    account,
                    description,
                    new Date()
            );
            save(notification);
        }
    }

    @Override
    public void createNotificationForDisapprovingFeedbackFromAdmin(Feedback feedback){
        if(feedback != null && feedback.getAppointment() != null && feedback.getAppointment().getAccount() != null){
            Appointment appointment = feedback.getAppointment();
            Account account = feedback.getAppointment().getAccount();
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdfDate.format(appointment.getAppointmentDate());
            String time = appointment.getAppointmentTime().split("-")[0];
            String description = "Phản hồi cho lịch hẹn vào lúc " + time + " ngày " + date +
                    " tại trung tâm nha khoa RaDe " + appointment.getBranch().getName() + " không được phê duyệt vi phạm quy tắc cộng động.";
            Notification notification = new Notification(
                    account,
                    description,
                    new Date()
            );
            save(notification);
        }
    }

    @Override
    public void createNotificationFromAdmin(String description){
        if(description != null){
            description = description.trim();
            Notification notification = new Notification(
                    null,
                    description,
                    new Date()
            );
            save(notification);
        }
    }
}
