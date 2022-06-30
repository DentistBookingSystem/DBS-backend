package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.componentform.AppointmentComponentForFilter;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.services.AppointmentService;
import com.rade.dentistbookingsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        int size = 20;
        if (i == null) i = 1;
        return appointmentService.findAll(PageRequest.of(i - 1, size, Sort.by("id").descending()));
    }

    @PostMapping("filter")
    public List<Appointment> findAppointmentByMakingDateAndStatus(@RequestBody AppointmentComponentForFilter appointmentComponentForFilter) {
        try {
            return appointmentService.filterAppointment(appointmentComponentForFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
