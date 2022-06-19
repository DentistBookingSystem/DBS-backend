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
    @Query(value = "UPDATE Appointment SET status = :status WHERE id = :id", nativeQuery = true)
    void check(@Param("status") Integer status, @Param("id") Integer id);

    List<Appointment> findByAccountId(int account_id, Pageable pageable);

    @Query(value = "SELECT Appointment.* FROM Appointment  WHERE id = ?1", nativeQuery = true)
    Appointment findId(int id);

    @Query(value =
            "SELECT Appointment.* " +
                    "FROM Appointment " +
                    "WHERE " +
                    "(doctor_id = :doctor_id OR :doctor_id = 0) AND " +
                    "(status = 0 OR status = 4) AND " +
                    "appointment_date = :time",
            nativeQuery = true)
    List<Appointment> findByDoctorIdAndTime(
                                        @Param("doctor_id") int doctor_id,
                                        @Param("time") String time);

    Appointment findByAccountAndStatusIn(Account account, int[] status);

    int countByAccountIdAndStatus(int account_id, int status);

    @Query(value =
            "SELECT " +
                    "            CASE WHEN EXISTS ( " +
                    "                    SELECT a.*, DATEDIFF(DAY, a.appointment_date, GETDATE())  " +
                    "                    FROM Appointment a  " +
                    "                    WHERE a.id = :id AND (a.status = 0 OR a.status = 4) AND " +
                    "                    DATEDIFF(DAY, a.appointment_date, GETDATE()) <= 1 AND a.account_id = :account_id)  " +
                    "                    THEN 'TRUE'  " +
                    "                    ELSE 'FALSE'  " +
                    "            END",
            nativeQuery = true)
    boolean checkAppointmentToCancel(@Param("id") int id, @Param("account_id") int account_id);

    @Query(value =
            "SELECT  " +
                    "                    CASE WHEN not exists (  " +
                    "                    SELECT COUNT (a.account_id)  " +
                    "                    FROM Appointment a  " +
                    "                    WHERE a.account_id = :account_id AND (a.status = 3) AND DATEDIFF(DAY, a.appointment_date, GETDATE()) < 30  " +
                    "                    GROUP BY a.account_id  " +
                    "                    ) OR (  " +
                    "                    SELECT COUNT (a.account_id)  " +
                    "                    FROM Appointment a  " +
                    "                    WHERE a.account_id = :account_id AND (a.status = 3) AND DATEDIFF(DAY, a.appointment_date, GETDATE()) < 30  " +
                    "                    GROUP BY a.account_id  " +
                    "                    ) < 3  " +
                    "                    THEN 'TRUE'  " +
                    "                    ELSE 'FALSE'  " +
                    "                    END",
            nativeQuery = true)
    boolean checkCountAppointmentToCancel(@Param("account_id") int account_id);
    @Modifying
    @Transactional
    @Query(value =
            "UPDATE Appointment SET status = 2 " +
            "WHERE (status = 0 OR status = 4) AND DATEDIFF(MINUTE," +
            "(CAST(appointment_date AS varchar) + ' ' + SUBSTRING(appointment_time, 0, 6) + ':00')," +
            "GETDATE()) > 15",
            nativeQuery = true)
    void checkAllAppointmentToMarkAbsent();
}
