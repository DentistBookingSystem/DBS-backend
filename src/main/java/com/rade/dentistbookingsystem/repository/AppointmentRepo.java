package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Account;
import com.rade.dentistbookingsystem.domain.Appointment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    final static int ABSENT_TIME_IN_A_ROW_TO_BAN = 3;
    final static int ABSENT_STATUS = 2;
    final static int CANCEL_STATUS = 3;
    final static int WAITING_STATUS = 0;
    final static int CANCEL_TIMES_LIMIT = 3;
    final static int DAYS_BEFORE_ALLOW_TO_CANCEL_OR_UPDATE = 1;
    final static int TIME_FOR_MARK_ABSENT_AS_MINUTE = 15;
    final static int DAYS_INTERVAL_FOR_CANCEL_APPOINTMENT = 30;
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
                    "(status = "+WAITING_STATUS+") AND " +
                    "appointment_date = :time",
            nativeQuery = true)
    List<Appointment> findByDoctorIdAndTime(
            @Param("doctor_id") int doctorId,
            @Param("time") String time);

    Appointment findByAccountAndStatusIn(Account account, int[] status);

    int countByAccountIdAndStatus(int accountId, int status);

    @Query(value =
            "SELECT " +
                    "            CASE WHEN EXISTS ( " +
                    "                    SELECT a.*, DATEDIFF(DAY, a.appointment_date, GETDATE())  " +
                    "                    FROM Appointment a  " +
                    "                    WHERE a.id = :id AND (a.status = "+WAITING_STATUS+") AND " +
                    "                    DATEDIFF(DAY, a.appointment_date, GETDATE()) <= -"+DAYS_BEFORE_ALLOW_TO_CANCEL_OR_UPDATE+" AND a.account_id = :account_id)  " +
                    "                    THEN 'TRUE'  " +
                    "                    ELSE 'FALSE'  " +
                    "            END",
            nativeQuery = true)
    boolean checkAppointmentToCancel(@Param("id") int id, @Param("account_id") int accountId);

    @Query(value =
            "SELECT  " +
                    "                    CASE WHEN not exists (  " +
                    "                    SELECT COUNT (a.account_id)  " +
                    "                    FROM Appointment a  " +
                    "                    WHERE a.account_id = :account_id AND (a.status = "+CANCEL_STATUS+") AND DATEDIFF(DAY, a.appointment_date, GETDATE()) < "+DAYS_INTERVAL_FOR_CANCEL_APPOINTMENT+"  " +
                    "                    GROUP BY a.account_id  " +
                    "                    ) OR (  " +
                    "                    SELECT COUNT (a.account_id)  " +
                    "                    FROM Appointment a  " +
                    "                    WHERE a.account_id = :account_id AND (a.status = "+CANCEL_STATUS+") AND DATEDIFF(DAY, a.appointment_date, GETDATE()) < "+DAYS_INTERVAL_FOR_CANCEL_APPOINTMENT+"  " +
                    "                    GROUP BY a.account_id  " +
                    "                    ) < "+CANCEL_TIMES_LIMIT+"  " +
                    "                    THEN 'TRUE'  " +
                    "                    ELSE 'FALSE'  " +
                    "                    END",
            nativeQuery = true)
    boolean checkCountAppointmentToCancel(@Param("account_id") int accountId);

    @Query(value =
            "SELECT Appointment.* " +
                    "FROM Appointment " +
                    "WHERE (status = 0) AND DATEDIFF(MINUTE," +
                    "(CAST(appointment_date AS varchar) + ' ' + SUBSTRING(appointment_time, 0, 6) + ':00')," +
                    "GETDATE()) > " + TIME_FOR_MARK_ABSENT_AS_MINUTE,
            nativeQuery = true)
    List<Appointment> findAllAppointmentToMarkAbsent();

    @Query(value =
            "SELECT TOP 1 Appointment.* " +
                    "FROM Appointment " +
                    "WHERE (status = 0) AND DATEDIFF(DAY, " +
                    "                    appointment_date, " +
                    "                    GETDATE()) >  -"+ (DAYS_BEFORE_ALLOW_TO_CANCEL_OR_UPDATE + 1) +" AND " +
                    "account_id = :account_id",
            nativeQuery = true)
    Appointment findAppointmentByAccountIdInNext24h(@Param("account_id") Integer accountId);

    @Query(value =
            "DECLARE @count_absent INT = 0 " +
                    "DECLARE @i INT = 0 " +
                    "DECLARE @count INT =  " +
                    "(SELECT COUNT(account_id) " +
                    "FROM Appointment " +
                    "WHERE account_id = :account_id) " +
                    "WHILE @i < @count " +
                    "BEGIN " +
                    "SET @count_absent = CASE (SELECT status " +
                    "FROM Appointment " +
                    "WHERE account_id = :account_id " +
                    "    ORDER BY id DESC " +
                    "OFFSET @i ROWS  " +
                    "FETCH NEXT 1 ROWS ONLY) " +
                    "                     WHEN " + ABSENT_STATUS + " THEN @count_absent + 1 " +
                    "ELSE 0 " +
                    "                   END  " +
                    "IF @count_absent >= " + ABSENT_TIME_IN_A_ROW_TO_BAN +" BEGIN BREAK END " +
                    "    SET @i = @i + 1 " +
                    "END " +
                    "SELECT " +
                    "CASE WHEN @count_absent >= " + ABSENT_TIME_IN_A_ROW_TO_BAN +" " +
                    "THEN 'TRUE' " +
                    "ELSE 'FALSE' " +
                    "END", nativeQuery = true)
    boolean checkAccountToBanByAppointment(@Param("account_id") int accountId);

    @Query(value =
            "DECLARE @count_absent INT = 0  " +
                    "DECLARE @i INT = 0  " +
                    "DECLARE @count INT =   " +
                    "(SELECT COUNT(account_id) " +
                    "FROM Appointment " +
                    "WHERE account_id = :account_id) " +
                    "WHILE @i < @count " +
                    "BEGIN " +
                    "SET @count_absent = CASE (SELECT status " +
                    "FROM Appointment " +
                    "WHERE account_id = :account_id " +
                    "ORDER BY id DESC " +
                    "OFFSET @i ROWS " +
                    "FETCH NEXT 1 ROWS ONLY) " +
                    "WHEN "+ ABSENT_STATUS +" " +
                    "THEN @count_absent + 1 " +
                    "ELSE 0 " +
                    "END " +
                    "IF @count_absent >= "+ ABSENT_TIME_IN_A_ROW_TO_BAN +" BEGIN BREAK END " +
                    "SET @i = @i +  1 " +
                    "END " +
                    "SET @count_absent = 0 " +
                    "SET @i = @i + 1 " +
                    "SET @count = " +
                    "(SELECT COUNT(account_id) " +
                    "FROM Appointment " +
                    "WHERE account_id = :account_id) " +
                    "WHILE @i < @count " +
                    "BEGIN " +
                    "SET @count_absent = CASE (SELECT status " +
                    "FROM Appointment " +
                    "WHERE account_id = :account_id " +
                    "ORDER BY id DESC " +
                    "OFFSET @i ROWS " +
                    "FETCH NEXT 1 ROWS ONLY) " +
                    "WHEN "+ ABSENT_STATUS +" " +
                    "THEN @count_absent + 1 " +
                    "ELSE 0 " +
                    "END " +
                    "IF @count_absent >= "+ABSENT_TIME_IN_A_ROW_TO_BAN+" BEGIN BREAK END " +
                    "SET @i = @i +  1 " +
                    "END " +
                    "SELECT " +
                    "CASE WHEN @count_absent >= "+ ABSENT_TIME_IN_A_ROW_TO_BAN +" " +
                    "THEN 'FALSE' " +
                    "ELSE 'TRUE' " +
                    "END", nativeQuery = true)
    boolean isAbleToUnBan(@Param("account_id") int accountId);

    @Query(value =
            "SELECT DISTINCT Appointment.* " +
                    "FROM " +
                    "Appointment, Account a, Appointment_Detail ad " +
                    "WHERE Appointment.account_id = a.id " +
                    "AND Appointment.id = ad.appointment_id " +
                    "AND (Appointment.status IN (:status)) AND " +
                    "(Appointment.appointment_date = CAST(:date as date) OR :date IS NULL) AND " +
                    "(a.phone LIKE CONCAT('%',:phone,'%') OR :phone IS NULL OR :phone = '') AND " +
                    "(Appointment.branch_id = :branchId OR :branchId = 0) AND " +
                    "(Appointment.doctor_id = :doctorId OR :doctorId = 0) AND " +
                    "(ad.service_id = :serviceId OR :serviceId = 0) " +
                    "ORDER BY Appointment.id DESC",
            nativeQuery = true)
    List<Appointment> filterAppointment(@Param("status") List<Integer> status,
                                        @Param("date") String date,
                                        @Param("phone") String phone,
                                        @Param("branchId") Integer branchId,
                                        @Param("doctorId") Integer doctorId,
                                        @Param("serviceId") Integer serviceId);

    @Query(value =
            "SELECT DISTINCT Appointment.* " +
                    "FROM " +
                    "Appointment, Account a, Appointment_Detail ad " +
                    "WHERE Appointment.account_id = a.id " +
                    "AND Appointment.id = ad.appointment_id AND " +
                    "(Appointment.appointment_date = CAST(:date as date) OR :date IS NULL) AND " +
                    "(a.phone LIKE CONCAT('%',:phone,'%') OR :phone IS NULL OR :phone = '') AND " +
                    "(Appointment.branch_id = :branchId OR :branchId = 0) AND " +
                    "(Appointment.doctor_id = :doctorId OR :doctorId = 0) AND " +
                    "(ad.service_id = :serviceId OR :serviceId = 0) " +
                    "ORDER BY Appointment.id DESC",
            nativeQuery = true)
    List<Appointment> filterAppointment(@Param("date") String date,
                                        @Param("phone") String phone,
                                        @Param("branchId") Integer branchId,
                                        @Param("doctorId") Integer doctorId,
                                        @Param("serviceId") Integer serviceId);
}
