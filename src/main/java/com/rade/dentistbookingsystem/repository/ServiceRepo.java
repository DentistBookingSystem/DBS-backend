package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.domain.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepo extends JpaRepository<Service, Integer> {
    public List<Service> findByServiceType(ServiceType serviceType);

    public List<Service> findByServiceTypeId(int id);

    public Service findByName(String name);

    @Query(value = "SELECT * FROM Service WHERE id = ?1", nativeQuery = true)
    Service findId(Integer id);

    public List<Service> findByServiceTypeIdAndStatus(int id, short status);

    public List<Service> findByStatus(short status);

    public Page<Service> findAllByStatus(short status, Pageable pageable);
}
