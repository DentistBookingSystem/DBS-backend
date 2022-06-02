package com.rade.dentistbookingsystem.controller;

import com.rade.dentistbookingsystem.componentform.AppointmentComponent;
import com.rade.dentistbookingsystem.componentform.ServiceDiscountComponent;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.domain.AppointmentDetail;
import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.error.AppointmentError;
import com.rade.dentistbookingsystem.jsonresponse.JsonAppointment;
import com.rade.dentistbookingsystem.model.AppointmentDTO;
import com.rade.dentistbookingsystem.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("rade/appointment")
public class AppointmentController {
    @Autowired
    ServiceSv serviceSv;
    @Autowired
    ServiceTypeSv serviceTypeSv;
    @Autowired
    BranchService branchService;
    @Autowired
    DiscountService discountService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    AppointmentDetailService appointmentDetailService;

    @PostMapping("")
    public AppointmentComponent makeAppointment(@RequestBody String branch_id) {
        int id = Integer.parseInt(branch_id);
        ArrayList<ServiceDiscountComponent> serviceDiscountComponentList = new ArrayList<>();
        for (Service service : serviceSv.findAll()) {
            serviceDiscountComponentList.add(new ServiceDiscountComponent(
                    service,
                    discountService.findAvailableByServiceId(service.getId())
            ));
        }
        return new AppointmentComponent(
                new AppointmentDTO(id),
                new AppointmentError(),
                serviceDiscountComponentList,
                serviceTypeSv.findAll(),
                branchService.findId(id),
                doctorService.findByBranchId(id)
        );
    }

    @PostMapping("make")
    public void makeAppointment(@RequestBody @Valid JsonAppointment jsonAppointment) {
        Appointment appointment = appointmentService.save(jsonAppointment.getAppointmentDTO());
        int noOfServiceId = jsonAppointment.getServiceIdList().length;
        for (int i = 0; i < noOfServiceId; i++) {
            AppointmentDetail appointmentDetail = new AppointmentDetail(
                    appointment,
                    serviceSv.findId(jsonAppointment.getServiceIdList()[i]),
                    doctorService.findId(jsonAppointment.getDoctorIdList()[i]),
                    discountService.findAvailableByServiceId(jsonAppointment.getServiceIdList()[i]));
            appointmentDetailService.save(appointmentDetail);
        }
    }
}
