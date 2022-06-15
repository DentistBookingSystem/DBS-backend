package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.domain.Appointment;
import com.rade.dentistbookingsystem.domain.Feedback;
import com.rade.dentistbookingsystem.domain.ServiceType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Appointment SET Status = ?1 WHERE id = ?2", nativeQuery = true)
    void check(Integer status, Integer id);

    List<Appointment> findByAccountId(int account_id, Pageable pageable);

    @Query(value = "SELECT Appointment.* FROM Appointment  WHERE id = ?1", nativeQuery = true)
    Appointment findId(int id);

    @Query(value =
            "SELECT Appointment.* " +
                    "FROM Appointment " +
                    "WHERE doctor_id = :doctor_id AND " +
                    "status = 0 AND " +
                    "DATEDIFF(day, appointment_date, :time) >= -7",
            nativeQuery = true)
    List<Appointment> checkShiftOfDoctor(@Param("doctor_id") int doctor_id,
                                  @Param("time") String time);

    @Query(value =
            "SELECT Appointment.* " +
                    "FROM Appointment " +
                    "WHERE " +
                    "(doctor_id = :doctor_id OR :doctor_id = 0) AND " +
                    "status = 0 AND " +
                    "appointment_date = :time",
            nativeQuery = true)
    List<Appointment> checkShiftOfDoctorOneDay(
                                        @Param("doctor_id") int doctor_id,
                                        @Param("time") String time);

    Appointment findByShiftAndDateAndDoctorId(int appointment_shift, Date appointment_date, int doctor_id);

    Appointment findByAccountAndStatus(Account account, int status);
}
