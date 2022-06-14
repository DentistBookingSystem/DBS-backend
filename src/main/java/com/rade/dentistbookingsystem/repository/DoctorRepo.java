package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Doctor;
import com.rade.dentistbookingsystem.domain.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    public List<Doctor> findByBranchId(int branch_id);

    @Query(value = "SELECT * FROM Doctor WHERE id = ?1", nativeQuery = true)
    Doctor findId(Integer id);

    public int countByBranchId(int branch_id);

    @Query(value =
            "SELECT Doctor.id " +
            "FROM Doctor LEFT JOIN Appointment a ON Doctor.id = a.doctor_id " +
            "WHERE a.appointment_date = :time AND a.branch_id = :branch_id " +
            "GROUP BY Doctor.id " +
            "HAVING COUNT(a.appointment_shift) <= ALL ( " +
                "SELECT COUNT(a.appointment_shift) " +
                "FROM Doctor LEFT JOIN Appointment a ON Doctor.id = a.doctor_id " +
                "WHERE a.appointment_date = :time AND a.branch_id = :branch_id " +
                "GROUP BY Doctor.id " +
            ")", nativeQuery = true)
    List<Integer> findDoctorIdLeastShiftOneDay(@Param("time") String time, @Param("branch_id") int branch_id);
}
