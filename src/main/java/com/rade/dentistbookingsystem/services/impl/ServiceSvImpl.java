package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.domain.ServiceType;
import com.rade.dentistbookingsystem.model.ServiceDTO;
import com.rade.dentistbookingsystem.repository.ServiceRepo;
import com.rade.dentistbookingsystem.services.ServiceSv;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceSvImpl implements ServiceSv {
    ServiceRepo serviceRepo;
    @Autowired
    ServiceTypeSv serviceTypeSv;

    public ServiceSvImpl(ServiceRepo serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    @Override
    public List<Service> findByServiceType(ServiceType serviceType) {
        return serviceRepo.findByServiceType(serviceType);
    }

    @Override
    public <S extends Service> S save(S entity) {
        return serviceRepo.save(entity);
    }

    @Override
    public Service insert(ServiceDTO serviceDTO) throws Exception {
        Service service = new Service();
        service.setName(serviceDTO.getName());
        service.setStatus(serviceDTO.getStatus());
        service.setDescription(serviceDTO.getDescription());
        service.setMin_price(serviceDTO.getMin_price());
        service.setMax_price(serviceDTO.getMax_price());
        service.setUrl(serviceDTO.getUrl());
        service.setServiceType(serviceTypeSv.findById(serviceDTO.getService_type_id()).orElseThrow(() -> new RuntimeException("Service type id not found")));
        return save(service);

    }


    public Service findId(Integer id) {
        return serviceRepo.findId(id);
    }

    public List<Service> findByServiceTypeId(int id) {
        return serviceRepo.findByServiceTypeId(id);
    }

    @Override
    public List<Service> findAll() {
        return serviceRepo.findAll();
    }

    @Override
    public Optional<Service> findById(Integer integer) {
        return serviceRepo.findById(integer);
    }

    @Override
    public Service edit(ServiceDTO serviceDTO, int id) {
        Optional<Service> serviceData = findById(id);
        if (serviceData.isPresent()) {
            Service service = serviceData.get();
            service.setName(serviceDTO.getName());
            service.setDescription(serviceDTO.getDescription());
            service.setStatus(serviceDTO.getStatus());
            service.setUrl(serviceDTO.getUrl());
            service.setMin_price(serviceDTO.getMin_price());
            service.setMax_price(serviceDTO.getMax_price());
            service.setServiceType(serviceTypeSv.findById(serviceDTO.getService_type_id()).orElseThrow(() -> new RuntimeException("Service type not found")));

            return save(service);
        }
        return null;

    }

    @Override
    public boolean existsById(Integer integer) {
        return serviceRepo.existsById(integer);
    }

    @Override
    public Service deleteService(int id) {
        Optional<Service> serviceData = findById(id);
        if (serviceData.isPresent()) {
            Service service = serviceData.get();
            service.setStatus((short) 0);
            return save(service);
        }
        return null;

    }

    // load danh service có status bằng 1, active
    @Override
    public List<Service> loadAllActiveService() {
        List<Service> activeList = new ArrayList<>();
        List<Service> serviceList = serviceRepo.findAll();
        for (Service service : serviceList) {
            if (service.getStatus() == 1) {
                activeList.add(service);
            }
        }
        return activeList;
    }

    // load all service component

    @Override
    public List<Service> loadServicesByName(String name) {
        return serviceRepo.findByName(name);
    }

}
