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
                    jsonAppointment.getAppointmentDTO().setDoctor_id(doctorService.findDoctorIdLeastShiftOneDay(date, branch_id).get(0));
                }
                if (appointmentService.findByShiftAndDateAndDoctorId(
                        jsonAppointment.getAppointmentDTO().getShift(),
                        sdf.parse(jsonAppointment.getAppointmentDTO().getDate()),
                        jsonAppointment.getAppointmentDTO().getDoctor_id()
                ) != null) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
                Appointment appointment = appointmentService.save(jsonAppointment.getAppointmentDTO());
                int noOfServiceId = jsonAppointment.getServiceIdList().length;
                if (noOfServiceId == 0) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
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
                shiftBookedByDateList.add(new ShiftBookedByDate(sdf.format(appointment.getDate()), appointment.getShift()));
            }
        }
        return shiftBookedByDateList;
    }

    @PostMapping("check-doctor")
    public ShiftBookedByDate checkShiftOfDoctorOneDay(@RequestBody DoctorAndDate doctorAndDate) throws ParseException {
        boolean isExist;
        int [] shift;
        int noOfShift = doctorService.countByBranchId(doctorAndDate.getBranch_id());
        if(doctorAndDate.getDoctor_id() == 0){
            shift = new int[]{
                    noOfShift,
                    noOfShift,
                    noOfShift,
                    noOfShift,
                    noOfShift,
                    noOfShift
            };
        }
        else{
            shift = new int[]{
                    1,
                    1,
                    1,
                    1,
                    1,
                    1
            };
        }
        String date = doctorAndDate.getDate();
        List<Appointment> appointmentList = appointmentService.checkShiftOfDoctorOneDay(doctorAndDate.getDoctor_id(), date);
        ShiftBookedByDate shiftBookedByDate = new ShiftBookedByDate(date);
        for (Appointment appointment : appointmentList) {
            shift[appointment.getShift()-1] = shift[appointment.getShift()-1] - 1;
        }
        for (int i = 0; i < 6; i++) {
            if (shift[i] > 0) shiftBookedByDate.getShiftList().add(i + 1);
        }
        return shiftBookedByDate;
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
}
