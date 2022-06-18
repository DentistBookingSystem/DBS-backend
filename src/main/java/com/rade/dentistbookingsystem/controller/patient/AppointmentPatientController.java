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
    public AppointmentComponent chooseBranch(@PathVariable int branch_id){
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Account account = accountService.findByPhone(jsonAppointment.getPhone());
            if(account.getStatus() == 2)
                return ResponseEntity.status(HttpStatus.LOCKED).build();
            if(appointmentService.findByAccountAndStatus(account, 0) != null)
                return ResponseEntity.status(HttpStatus.GONE).build();
            if(account != null){
                jsonAppointment.getAppointmentDTO().setAccount_id(account.getId());
                if(jsonAppointment.getAppointmentDTO().getDoctor_id() == 0){
                    String date = jsonAppointment.getAppointmentDTO().getDate();
                    int branch_id = jsonAppointment.getAppointmentDTO().getBranch_id();

                }

                return ResponseEntity.ok(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PostMapping("history")
    public List<Appointment> getHistoryList(@RequestBody PhoneAndPage phoneAndPage){
        String phone = phoneAndPage.getPhone();
        int account_id = accountService.findByPhone(phone).getId();
        int page = phoneAndPage.getPage();
        Pageable pageable = PageRequest.of(page - 1, 3, Sort.by("id").descending());
        return appointmentService.findByAccountId(account_id, pageable);
    }

    @GetMapping("history/{id}")
    public Appointment viewHistoryById(@PathVariable int id){
        return appointmentService.findId(id);
    }


    @GetMapping("cancel/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable Integer id){
        try{
            if(appointmentService.checkAppointmentToCancel(id)){
                appointmentService.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

//    @PostMapping("check-doctor")
//    public TimeOptionByDate checkTimeOptionOfDoctorByDate(@RequestBody DoctorAndDate doctorAndDate) throws ParseException {
//
//
//
//        return shiftBookedByDate;
//    }
}
