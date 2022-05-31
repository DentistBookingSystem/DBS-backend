package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.domain.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepo extends JpaRepository<Service, Integer> {
    public List<Service> findByServiceType(ServiceType serviceType);

    public List<Service> findByServiceTypeId(int id);


}
