package com.rade.dentistbookingsystem.services;

import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.domain.ServiceType;
import com.rade.dentistbookingsystem.model.ServiceDTO;

import java.util.List;
import java.util.Optional;

public interface ServiceSv {
    List<Service> findByServiceType(ServiceType serviceType);

    <S extends Service> S save(S entity);

    Service insert(ServiceDTO serviceDTO) throws Exception;

    List<Service> findByServiceTypeId(int id);

    List<ServiceType> findAll();

    Optional<Service> findById(Integer integer);

    Service edit(ServiceDTO serviceDTO, int id);

    boolean existsById(Integer integer);

    Service deleteService(int id);
}
