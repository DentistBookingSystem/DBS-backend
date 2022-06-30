package com.rade.dentistbookingsystem.controller.patient;

import com.rade.dentistbookingsystem.componentform.FeedbackAndPhone;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.AppointmentService;
import com.rade.dentistbookingsystem.services.FeedbackService;
import com.rade.dentistbookingsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("rade/patient/feedback")
public class FeedbackPatientController {
    @Autowired
    AccountService accountService;
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    AppointmentService appointmentService;

    @Autowired
    NotificationService notificationService;


    final static int BLOCKED_ACCOUNT_STATUS = 2;
    final static int APPOINTMENT_DONE_STATUS = 1;
    final static int APPOINTMENT_FEEDBACK_STATUS = 5;

    @PostMapping("send")
    public ResponseEntity<?> sendFeedback(@RequestBody FeedbackAndPhone feedbackAndPhone) {
        try {
            Account account = accountService.findByPhone(feedbackAndPhone.getPhone());
            if (account == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            if (account.getStatus() == BLOCKED_ACCOUNT_STATUS)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            Appointment appointment = appointmentService.findId(feedbackAndPhone.getFeedbackDTO().getAppointmentId());
            if (appointment == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            if (appointment.getAccount().getId() != account.getId()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            String content = feedbackAndPhone.getFeedbackDTO().getContent();
            if (!(appointment.getStatus() == APPOINTMENT_DONE_STATUS))
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            if (!(content != null && !content.isEmpty() && content.length() <= 150))
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            Feedback feedback = feedbackService.save(feedbackAndPhone.getFeedbackDTO());
            if (feedback != null) {
                appointmentService.check(APPOINTMENT_FEEDBACK_STATUS, feedback.getAppointment().getId());
                notificationService.createNotificationForSendingFeedback(feedback);
                return ResponseEntity.ok(feedback);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
}
