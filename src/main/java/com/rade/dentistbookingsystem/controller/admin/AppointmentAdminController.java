package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.componentform.StatusForAppointment;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.model.AppointmentDTO;
import com.rade.dentistbookingsystem.services.AppointmentService;
import com.rade.dentistbookingsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/appointment")
public class AppointmentAdminController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    NotificationService notificationService;

    @GetMapping("{i}")
    public Page<Appointment> getAppointmentList(@PathVariable Integer i) {
        if (i == null) i = 1;
        return appointmentService.findAll(PageRequest.of(i - 1, 20, Sort.by("id").descending()));
    }

    @PostMapping("check")
    public boolean checkAppointment(@RequestBody StatusForAppointment statusForAppointment) {
        appointmentService.check(statusForAppointment.getStatus(), statusForAppointment.getId());
        return true;
    }


    @GetMapping("filter")
    public List<Appointment> findAppointmentByMakingDateAndStatus(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            return appointmentService.filterAppointment(appointmentDTO);
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

    @GetMapping("/markdone")
    public ResponseEntity<?> checkDoneAppointment(@RequestParam int id) {

        return ResponseEntity.ok(appointmentService.checkDoneAppointmentForAdmin(id));


    }


}
