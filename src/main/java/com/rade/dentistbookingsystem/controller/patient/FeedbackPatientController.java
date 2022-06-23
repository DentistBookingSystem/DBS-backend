package com.rade.dentistbookingsystem.controller.patient;

import com.rade.dentistbookingsystem.componentform.FeedbackAndPhone;
import com.rade.dentistbookingsystem.componentform.PageForFeedback;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.model.FeedbackDTO;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.AppointmentService;
import com.rade.dentistbookingsystem.services.FeedbackService;
import com.rade.dentistbookingsystem.services.NotificationService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("send")
    public ResponseEntity<?> sendFeedback(@RequestBody FeedbackAndPhone feedbackAndPhone){
        try {
            Account account = accountService.findByPhone(feedbackAndPhone.getPhone());
            if(account == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            if(account.getStatus() == 2)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            Appointment appointment = appointmentService.findId(feedbackAndPhone.getFeedbackDTO().getAppointmentId());
            if(appointment == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
            if(appointment.getAccount().getId() != account.getId()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
            String content = feedbackAndPhone.getFeedbackDTO().getContent();
            if(!(appointment.getStatus()==1))
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            if(!(content != null && !content.isEmpty()))
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            Feedback feedback = feedbackService.save(feedbackAndPhone.getFeedbackDTO());
            if (feedback != null){
                appointmentService.check(5, feedback.getAppointment().getId());
                notificationService.createNotificationForSendingFeedback(feedback);
                return ResponseEntity.ok(feedback);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }
}
