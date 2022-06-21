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

import java.text.SimpleDateFormat;
import java.util.*;

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
        Account account = accountService.findId(appointmentDTO.getAccountId());
        try{
            Appointment appointment;
            if(appointmentDTO.getId() == null){
                appointment = new Appointment(
                        account,
                        branchService.findId(appointmentDTO.getBranchId()),
                        doctorService.findId(appointmentDTO.getDoctorId()),
                        dateFormat.parse(appointmentDTO.getDate()),
                        appointmentDTO.getTime(),
                        0,
                        new Date()
                );
            }
            else{
                appointment = findId(appointmentDTO.getId());
                appointment.setDoctor(doctorService.findId(appointmentDTO.getDoctorId()));
                appointment.setAppointmentDate(dateFormat.parse(appointmentDTO.getDate()));
                appointment.setAppointmentTime(appointmentDTO.getTime());
                appointment.setStatus(4);
            }
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
    public List<Appointment> findByAccountId(int accountId, Pageable pageable) {
        return appointmentRepo.findByAccountId(accountId, pageable);
    }

    @Override
    public Appointment findByAccountAndStatusIn(Account account, int[] status) {
        return appointmentRepo.findByAccountAndStatusIn(account, status);
    }

    @Override
    public boolean checkAppointmentToCancel(int id, int accountId) {
        return appointmentRepo.checkAppointmentToCancel(id, accountId);
    }

    @Override
    public List<String> checkTimeOptionOfDoctorByDate(DoctorAndDate doctorAndDate){
        List<String> validTimeOption = new ArrayList<>();
        try{
            List<String> timeOptionByDateList = new ArrayList<>();
            List<String> timeOptionBooked = new ArrayList<>();

            List<Appointment> appointmentList;
            if(doctorAndDate.getServiceId().length == 0) return null;
            float totalTime = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date startTimeAtMorning = branchService.findId(doctorAndDate.getBranchId()).getOpenTime();
            Date endTimeAtMorning = sdf.parse("12:00");
            Date startTimeAtNoon = sdf.parse("13:00");
            Date endTimeAtNoon = branchService.findId(doctorAndDate.getBranchId()).getCloseTime();
            boolean endOfMorning = false;
            boolean endOfNoon = false;
            if(doctorAndDate.getDoctorId() != 0){
                for (int serviceId: doctorAndDate.getServiceId()) {
                    totalTime = totalTime + serviceSv.findId(serviceId).getEstimatedTime();
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
                appointmentList = appointmentRepo.findByDoctorIdAndTime(doctorAndDate.getDoctorId(), doctorAndDate.getDate());
                Appointment tmp = null;
                Integer appointmentId = doctorAndDate.getAppointmentId();
                if(appointmentId != null) tmp = findId(appointmentId);
                for (Appointment appointment : appointmentList) {
                    if (tmp != null && appointment.getId() == tmp.getId()) continue;
                    timeOptionBooked.add(appointment.getAppointmentTime());
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
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return validTimeOption;
    }
    @Override
    public List<String> checkTimeOptionByDate(DoctorAndDate doctorAndDate) throws Exception{
        if(doctorAndDate.getDoctorId() != 0){
            return checkTimeOptionOfDoctorByDate(doctorAndDate);
        }
        else{
            List<Doctor> doctorList = doctorService.findByBranchId(doctorAndDate.getBranchId());
            List<String> generalOptionList = new ArrayList<>();
            for (Doctor doctor : doctorList) {
                doctorAndDate.setDoctorId(doctor.getId());
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
            jsonAppointment.getAppointmentDTO().setAccountId(account.getId());
            int doctorId = jsonAppointment.getAppointmentDTO().getDoctorId();
            String date = jsonAppointment.getAppointmentDTO().getDate();
            String time = jsonAppointment.getAppointmentDTO().getTime();
            int branchId = jsonAppointment.getAppointmentDTO().getBranchId();
            int [] serviceId = jsonAppointment.getServiceIdList();
            Integer appointmentId = jsonAppointment.getAppointmentDTO().getId();
            boolean valid = false;
            if(jsonAppointment.getAppointmentDTO().getDoctorId() != 0){
                DoctorAndDate doctorAndDate = new DoctorAndDate(appointmentId, branchId, doctorId, date, serviceId);
                for (String stringOption : checkTimeOptionByDate(doctorAndDate)) {
                    if (time.equals(stringOption)){
                        valid = true;
                        break;
                    }
                }
            }
            else{

                List<Doctor> doctorList = doctorService.findByBranchId(branchId);
                List<Doctor> availableDoctorList = new ArrayList<>();
                for (Doctor doctor : doctorList) {
                    DoctorAndDate doctorAndDate = new DoctorAndDate(appointmentId, branchId, doctor.getId(), date, serviceId);
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
                        Date start = sdf.parse(appointment.getAppointmentTime().split("-")[0]);
                        Date end = sdf.parse(appointment.getAppointmentTime().split("-")[1]);
                        totalTime.set(i, totalTime.get(i) + (float)((end.getTime() - start.getTime()) / (1000 * 60 * 60)));
                        i++;
                    }
                }
                int indexOfDoctor = totalTime.indexOf(Collections.min(totalTime));
                jsonAppointment.getAppointmentDTO().setDoctorId(availableDoctorList.get(indexOfDoctor).getId());
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
    @Override
    public boolean checkCountAppointmentToCancel(int accountId) {
        return appointmentRepo.checkCountAppointmentToCancel(accountId);
    }

    @Override
    public List<Appointment> findAllAppointmentToMarkAbsent() {
        return appointmentRepo.findAllAppointmentToMarkAbsent();
    }
    @Override
    public boolean checkAccountToBanByAppointment(int accountId) {
        return appointmentRepo.checkAccountToBanByAppointment(accountId);
    }
}
