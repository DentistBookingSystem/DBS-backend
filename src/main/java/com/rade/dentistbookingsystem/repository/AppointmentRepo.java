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
import org.springframework.web.bind.annotation.GetMapping;

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
                    "WHERE " +
                    "(doctor_id = :doctor_id OR :doctor_id = 0) AND " +
                    "status = 0 AND " +
                    "appointment_date = :time",
            nativeQuery = true)
    List<Appointment> findByDoctorIdAndTime(
                                        @Param("doctor_id") int doctor_id,
                                        @Param("time") String time);

    Appointment findByAccountAndStatus(Account account, int status);

    int countByAccountIdAndStatus(int account_id, int status);

    @Query(value =
            "SELECT " +
            "CASE WHEN EXISTS (" +
                    "SELECT a.*, DATEDIFF(MINUTE, a.time_making, GETDATE()) " +
                    "FROM Appointment a " +
                    "WHERE a.id = :id AND a.status = 0 AND DATEDIFF(MINUTE, a.time_making, GETDATE()) <= 3 " +
                    ") " +
                    "THEN 'TRUE' " +
                    "ELSE 'FALSE' " +
                    "END",
            nativeQuery = true)
    boolean checkAppointmentToCancel(@Param("id") int id);
    @Transactional
    void deleteById(int id);
}
