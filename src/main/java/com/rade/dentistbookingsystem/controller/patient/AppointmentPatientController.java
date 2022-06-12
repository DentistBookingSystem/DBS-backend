package com.rade.dentistbookingsystem.controller.patient;

import com.rade.dentistbookingsystem.componentform.*;
import com.rade.dentistbookingsystem.domain.*;
import com.rade.dentistbookingsystem.error.AppointmentError;
import com.rade.dentistbookingsystem.model.AppointmentDTO;
import com.rade.dentistbookingsystem.services.*;
import com.rade.dentistbookingsystem.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rade/patient/appointment")
public class AppointmentPatientController {
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
    @Autowired
    AccountService accountService;
    @GetMapping("{branch_id}")
    public AppointmentComponent makeAppointment(@PathVariable int branch_id){
        ArrayList<ServiceDiscountComponent> serviceDiscountComponentList = new ArrayList<>();
        for (Service service : serviceSv.findAll()) {
            serviceDiscountComponentList.add(new ServiceDiscountComponent(
                    service,
                    discountService.findAvailableByServiceId(service.getId())
            ));
        }
        return new AppointmentComponent(
        new AppointmentDTO(branch_id),
        new AppointmentError(),
        serviceDiscountComponentList,
        serviceTypeSv.findAll(),
        branchService.findId(branch_id),
        doctorService.findByBranchId(branch_id)
        );
    }

    @PostMapping("make")
    public ResponseEntity<?> makeAppointment(@RequestBody @Valid JsonAppointment jsonAppointment){
        try {
            Account account = accountService.findByPhone(jsonAppointment.getPhone());
            if(account != null){
                jsonAppointment.getAppointmentDTO().setAccount_id(account.getId());
                Appointment appointment = appointmentService.save(jsonAppointment.getAppointmentDTO());
                int noOfServiceId = jsonAppointment.getServiceIdList().length;
                for (int i = 0; i < noOfServiceId; i++) {
                    AppointmentDetail appointmentDetail = new AppointmentDetail(
                            appointment,
                            serviceSv.findId(jsonAppointment.getServiceIdList()[i]),
                            discountService.findAvailableByServiceId(jsonAppointment.getServiceIdList()[i]));
                    appointmentDetailService.save(appointmentDetail);
                }
                return ResponseEntity.ok(appointment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PostMapping("history")
    public List<Appointment> getHistoryList(@RequestBody PhoneAndPage phoneAndPage){
        String phone = phoneAndPage.getData()[0];
        int account_id = accountService.findByPhone(phone).getId();
        int page = Integer.parseInt(phoneAndPage.getData()[1]);
        Pageable pageable = PageRequest.of(page - 1, 3, Sort.by("id").descending());
        return appointmentService.findByAccountId(account_id, pageable);
    }

    @GetMapping("history/{id}")
    public Appointment viewHistoryById(@PathVariable int id){
        return appointmentService.findId(id);
    }

    @GetMapping("check-doctor/{doctor_id}")
    public ArrayList<ShiftBookedByDate> checkShiftOfDoctor(@PathVariable int doctor_id) throws ParseException {
        boolean isExist;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        List<Appointment> appointmentList = appointmentService.checkShiftOfDoctor(doctor_id, currentDate);
        ArrayList<ShiftBookedByDate> shiftBookedByDateList = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            isExist = false;
            for (ShiftBookedByDate shiftBookedByDate : shiftBookedByDateList) {
                if(shiftBookedByDate.getDate().toString().equals(appointment.getDate().toString())){
                    shiftBookedByDate.getShiftList().add(appointment.getShift());
                    isExist = true;
                }
            }
            if(!isExist){
                shiftBookedByDateList.add(new ShiftBookedByDate(appointment.getDate(), appointment.getShift()));
            }
        }
        return shiftBookedByDateList;
    }
}
