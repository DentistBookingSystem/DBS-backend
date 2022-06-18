package com.rade.dentistbookingsystem.services.impl;
import com.rade.dentistbookingsystem.componentform.DoctorAndDate;
import com.rade.dentistbookingsystem.componentform.JsonAppointment;
import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.domain.Doctor;
import com.rade.dentistbookingsystem.model.AppointmentDTO;
import com.rade.dentistbookingsystem.repository.AppointmentRepo;
import com.rade.dentistbookingsystem.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    AppointmentRepo appointmentRepo;
    @Autowired
    ServiceSv serviceSv;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BranchService branchService;
    @Autowired
    private DoctorService doctorService;
    public AppointmentServiceImpl(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public Appointment save(AppointmentDTO appointmentDTO){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Account account = accountService.findId(appointmentDTO.getAccount_id());
        try{
            Appointment appointment = new Appointment(
                    account,
                    branchService.findId(appointmentDTO.getBranch_id()),
                    doctorService.findId(appointmentDTO.getDoctor_id()),
                    dateFormat.parse(appointmentDTO.getDate()),
                    appointmentDTO.getTime(),
                    0,
                    new Date()
            );
            return appointmentRepo.save(appointment);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Appointment> findAll(Pageable pageable) {
        return appointmentRepo.findAll(pageable);
    }

    @Override
    public Appointment findId(int id) {
        return appointmentRepo.findId(id);
    }

    @Override
    public void check(Integer status, Integer id) {
        appointmentRepo.check(status, id);
    }
    @Override
    public List<Appointment> findByAccountId(int account_id, Pageable pageable) {
        return appointmentRepo.findByAccountId(account_id, pageable);
    }

    @Override
    public Appointment findByAccountAndStatus(Account account, int status) {
        return appointmentRepo.findByAccountAndStatus(account, status);
    }

    @Override
    public boolean checkAppointmentToCancel(int id) {
        return appointmentRepo.checkAppointmentToCancel(id);
    }

    @Override
    public void deleteById(int id) {
        appointmentRepo.deleteById(id);
    }

    @Override
    public List<String> checkTimeOptionOfDoctorByDate(DoctorAndDate doctorAndDate) throws Exception{
        List<String> timeOptionByDateList = new ArrayList<>();
        List<String> timeOptionBooked = new ArrayList<>();
        List<String> validTimeOption = new ArrayList<>();
        List<Appointment> appointmentList;
        if(doctorAndDate.getService_id().length == 0) return null;
        float totalTime = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date startTimeAtMorning = branchService.findId(doctorAndDate.getBranch_id()).getOpen_time();
        Date endTimeAtMorning = sdf.parse("12:00");
        Date startTimeAtNoon = sdf.parse("13:00");
        Date endTimeAtNoon = branchService.findId(doctorAndDate.getBranch_id()).getClose_time();
        boolean endOfMorning = false;
        boolean endOfNoon = false;
        if(doctorAndDate.getDoctor_id() != 0){
            for (int service_id: doctorAndDate.getService_id()) {
                totalTime = totalTime + serviceSv.findId(service_id).getEstimated_time();
            }
            while(!endOfMorning){
                String start = sdf.format(startTimeAtMorning);
                Calendar cal = Calendar.getInstance();
                cal.setTime(sdf.parse(start));
                cal.add(Calendar.MINUTE, Math.round(totalTime*60));

                Calendar calForEndOfMorning = Calendar.getInstance();
                calForEndOfMorning.setTime(endTimeAtMorning);

                if(!cal.after(calForEndOfMorning)){
                    String end = sdf.format(cal.getTime());
                    timeOptionByDateList.add(start+"-"+end);
                    cal.setTime(sdf.parse(start));
                    cal.add(Calendar.MINUTE, 30);
                    startTimeAtMorning = cal.getTime();
                }
                else{
                    endOfMorning = true;
                }
            }

            while(!endOfNoon){
                String start = sdf.format(startTimeAtNoon);
                Calendar cal = Calendar.getInstance();
                cal.setTime(sdf.parse(start));
                cal.add(Calendar.MINUTE, Math.round(totalTime*60));

                Calendar calForEndOfNoon = Calendar.getInstance();
                calForEndOfNoon.setTime(endTimeAtNoon);

                if(!cal.after(calForEndOfNoon)){
                    String end = sdf.format(cal.getTime());
                    timeOptionByDateList.add(start+"-"+end);
                    cal.setTime(sdf.parse(start));
                    cal.add(Calendar.MINUTE, 30);
                    startTimeAtNoon = cal.getTime();
                }
                else{
                    endOfNoon = true;
                }
            }
            appointmentList = appointmentRepo.findByDoctorIdAndTime(doctorAndDate.getDoctor_id(), doctorAndDate.getDate());
            for (Appointment appointment : appointmentList) {
                timeOptionBooked.add(appointment.getAppointment_time());
            }
            for (String option : timeOptionByDateList) {
                boolean valid = true;
                Date startTime = sdf.parse(option.split("-")[0]);
                Date endTime = sdf.parse(option.split("-")[1]);
                for (String optionBooked : timeOptionBooked) {
                    Date startTimeBooked = sdf.parse(optionBooked.split("-")[0]);
                    Date endTimeBooked = sdf.parse(optionBooked.split("-")[1]);
                    if(((startTimeBooked.before(endTime)) && (endTimeBooked.after(startTime)))){
                        valid = false;
                    }
                }
                if (valid) validTimeOption.add(option);
            }
        }
        return validTimeOption;
    }
    @Override
    public List<String> checkTimeOptionByDate(DoctorAndDate doctorAndDate) throws Exception{
        if(doctorAndDate.getDoctor_id() != 0){
            return checkTimeOptionOfDoctorByDate(doctorAndDate);
        }
        else{
            List<Doctor> doctorList = doctorService.findByBranchId(doctorAndDate.getBranch_id());
            List<String> generalOptionList = new ArrayList<>();
            for (Doctor doctor : doctorList) {
                doctorAndDate.setDoctor_id(doctor.getId());
                List<String> option = checkTimeOptionOfDoctorByDate(doctorAndDate);
                option.removeAll(generalOptionList);
                generalOptionList.addAll(option);
            }
            return generalOptionList;
        }
    }
    @Override
    public Appointment checkValidAndSave(JsonAppointment jsonAppointment){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Account account = accountService.findByPhone(jsonAppointment.getPhone());
            jsonAppointment.getAppointmentDTO().setAccount_id(account.getId());
            int doctor_id = jsonAppointment.getAppointmentDTO().getDoctor_id();
            String date = jsonAppointment.getAppointmentDTO().getDate();
            String time = jsonAppointment.getAppointmentDTO().getTime();
            int branch_id = jsonAppointment.getAppointmentDTO().getBranch_id();
            int [] service_id = jsonAppointment.getServiceIdList();

            boolean valid = false;
            if(jsonAppointment.getAppointmentDTO().getDoctor_id() != 0){
                DoctorAndDate doctorAndDate = new DoctorAndDate(branch_id, doctor_id, date, service_id);
                for (String stringOption : checkTimeOptionByDate(doctorAndDate)) {
                    if (time.equals(stringOption)){
                        valid = true;
                        break;
                    }
                }
            }
            else{
                List<Doctor> doctorList = doctorService.findByBranchId(branch_id);
                List<Doctor> availableDoctorList = new ArrayList<>();
                for (Doctor doctor : doctorList) {
                    DoctorAndDate doctorAndDate = new DoctorAndDate(branch_id, doctor.getId(), date, service_id);
                    for (String stringOption : checkTimeOptionByDate(doctorAndDate)) {
                        if (time.equals(stringOption)){
                            availableDoctorList.add(doctor);
                            break;
                        }
                    }
                }
                List<Float> totalTime = new ArrayList<>();
                int i = 0;
                for (Doctor doctor : availableDoctorList) {
                    totalTime.add((float) 0);
                    List<Appointment> appointmentList = appointmentRepo.findByDoctorIdAndTime(doctor.getId(), date);
                    for (Appointment appointment : appointmentList) {
                        Date start = sdf.parse(appointment.getAppointment_time().split("-")[0]);
                        Date end = sdf.parse(appointment.getAppointment_time().split("-")[1]);
                        totalTime.set(i, totalTime.get(i) + (float)((end.getTime() - start.getTime()) / (1000 * 60 * 60)));
                    }
                }
                int indexOfDoctor = totalTime.indexOf(Collections.min(totalTime));
                jsonAppointment.getAppointmentDTO().setDoctor_id(availableDoctorList.get(indexOfDoctor).getId());
                valid = true;
            }
            if(valid) return save(jsonAppointment.getAppointmentDTO());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
