package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.domain.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepo extends JpaRepository<Service, Integer> {
    public List<Service> findByServiceType(ServiceType serviceType);

    public List<Service> findByServiceTypeId(int id);

    public Service findByName(String name);

    @Query(value = "SELECT * FROM Service WHERE id = ?1", nativeQuery = true)
    Service findId(Integer id);

    public List<Service> findByServiceTypeIdAndStatus(int id, short status);

    @Query(value = "SELECT Service.* " +
            "FROM Service, Appointment_Detail ad, Appointment a " +
            "WHERE Service.id = ad.service_id AND ad.appointment_id = a.id AND a.id = :appointment_id", nativeQuery = true)
    List<Service> findByAppointmentId(@Param("appointment_id") Integer appointment_id);
}
