package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.componentform.StatusForAppointment;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.model.AppointmentDTO;
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

    @GetMapping("filter/{i}")
    public List<Appointment> findAppointmentByMakingDateAndStatus(@RequestBody AppointmentDTO appointmentDTO, @PathVariable int i) {
        try {
            Pageable pageable = PageRequest.of(i - 1, 3, Sort.by("id").ascending());
            return appointmentService.filterAppointment(appointmentDTO, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
