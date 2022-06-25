package com.rade.dentistbookingsystem.controller.staff;

import com.rade.dentistbookingsystem.componentform.AppointmentComponentForFilter;
import com.rade.dentistbookingsystem.componentform.AppointmentWithDetails;
import com.rade.dentistbookingsystem.componentform.JsonPhone;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.domain.AppointmentDetail;
import com.rade.dentistbookingsystem.model.AppointmentDTO;
import com.rade.dentistbookingsystem.services.AccountService;
import com.rade.dentistbookingsystem.services.AppointmentDetailService;
import com.rade.dentistbookingsystem.services.AppointmentService;
import com.rade.dentistbookingsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/staff/appointment")
public class AppointmentStaffController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    NotificationService notificationService;

    @Autowired
    AppointmentDetailService appointmentDetailService;

    @Autowired
    AccountService accountService;

    @PostMapping("filter/{i}")
    public List<Appointment> findAppointmentByMakingDateAndStatus(@RequestBody AppointmentComponentForFilter appointmentComponentForFilter, @PathVariable int i) {
        try {
            Pageable pageable = PageRequest.of(i - 1, 3, Sort.by("id").ascending());
            return appointmentService.filterAppointment(appointmentComponentForFilter, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("find/{id}")
    public AppointmentWithDetails findAppointmentByPhone(@PathVariable Integer id) {
        try {
            Appointment appointment = appointmentService.findId(id);
            List<AppointmentDetail> appointmentDetailList = appointmentDetailService.findByAppointmentId(appointment.getId());
            return new AppointmentWithDetails(appointment, appointmentDetailList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("cancel")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> cancelAppointment(@RequestParam int id, @RequestParam String description) {
        Appointment appointment = appointmentService.cancelAppointmentForAdmin(id);
        notificationService.createNotificationForCancellingAppointmentFromAdmin(appointment, description);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping("markdone/{id}")
    public ResponseEntity<?> checkDoneAppointment(@PathVariable int id) {
        return ResponseEntity.ok(appointmentService.checkDoneAppointmentForAdmin(id));
    }
}
