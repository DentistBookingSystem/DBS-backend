package com.rade.dentistbookingsystem.controller.admin;

import com.rade.dentistbookingsystem.componentform.AppointmentComponentForFilter;
import com.rade.dentistbookingsystem.componentform.AppointmentWithDetails;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.domain.AppointmentDetail;
import com.rade.dentistbookingsystem.services.AppointmentDetailService;
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

    @Autowired
    AppointmentDetailService appointmentDetailService;

    @PostMapping("filter")
    public List<Appointment> findAppointmentByMakingDateAndStatus(@RequestBody AppointmentComponentForFilter appointmentComponentForFilter) {
        try {
            return appointmentService.filterAppointment(appointmentComponentForFilter);
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
}
