package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.componentform.AppointmentComponentForFilter;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/admin/appointment")
public class AppointmentAdminController {
    @Autowired
    AppointmentService appointmentService;

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
