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
                    "(status = 0) AND " +
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
                    "                    WHERE a.id = :id AND (a.status = 0) AND " +
                    "                    DATEDIFF(DAY, a.appointment_date, GETDATE()) <= 1 AND a.account_id = :account_id)  " +
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
    boolean checkCountAppointmentToCancel(@Param("account_id") int accountId);

    @Query(value =
            "SELECT Appointment.* " +
                    "FROM Appointment " +
                    "WHERE (status = 0) AND DATEDIFF(MINUTE," +
                    "(CAST(appointment_date AS varchar) + ' ' + SUBSTRING(appointment_time, 0, 6) + ':00')," +
                    "GETDATE()) > 15 ",
            nativeQuery = true)
    List<Appointment> findAllAppointmentToMarkAbsent();

    @Query(value =
            "SELECT TOP 1 Appointment.* " +
                    "FROM Appointment " +
                    "WHERE (status = 0) AND DATEDIFF(HOUR," +
                    "(CAST(appointment_date AS varchar) + ' ' + SUBSTRING(appointment_time, 0, 6) + ':00')," +
                    "GETDATE()) <= 24 AND " +
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
                    "                     WHEN 2 THEN @count_absent + 1 " +
                    "ELSE 0 " +
                    "                   END  " +
                    "IF @count_absent >= 3 BEGIN BREAK END " +
                    "    SET @i = @i + 1 " +
                    "END " +
                    "SELECT " +
                    "CASE WHEN @count_absent >= 3 " +
                    "THEN 'TRUE' " +
                    "ELSE 'FALSE' " +
                    "END", nativeQuery = true)
    boolean checkAccountToBanByAppointment(@Param("account_id") int accountId);


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

    //Appointment findByAccountIdAndAppointmentDateAndStatusIn(Integer accountId, Date date, int[] status);

//    @Query(value = "SELECT a FROM Appointment a join a.account"
//            + " WHERE (a.status = :status OR :status IS NULL) AND " +
//            // "(a.appointmentDate = :appointmentDate  OR :appointmentDate IS NULL) AND " +
//            //"(a.account.phone = :phone OR :phone IS NULL)" +
//            "(a.branch.id = :branchId OR :branchId IS NULL) AND " +
//            "(a.doctor.id = :doctorId OR :doctorId IS NULL)")
//    List<Appointment> filter(@Param("status") int status, @Param("branchId") int branchId, @Param("doctorId") int doctorId);

//    List<Appointment> findByStatusInOrAppointmentDateOrAccount_PhoneOrDoctor_IdOrBranch_Id(int[] status, Date appointmentDate, String account_phone, int doctor_id, int branch_id, Pageable pageable);
    //List<Appointment> findByStatusNullOrStatusAndAppointmentDateNullOrAppointmentDateDAndDoctorIsNull;
}
