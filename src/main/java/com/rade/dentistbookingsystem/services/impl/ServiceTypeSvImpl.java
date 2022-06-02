package com.rade.dentistbookingsystem.services.impl;

import com.rade.dentistbookingsystem.domain.ServiceType;
import com.rade.dentistbookingsystem.model.ServiceTypeDTO;
import com.rade.dentistbookingsystem.repository.ServiceTypeRepo;
import com.rade.dentistbookingsystem.services.ServiceTypeSv;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTypeSvImpl implements ServiceTypeSv {
    ServiceTypeRepo serviceTypeRepo;

    public ServiceTypeSvImpl(ServiceTypeRepo serviceTypeRepo) {
        this.serviceTypeRepo = serviceTypeRepo;
    }

    public List<ServiceType> findAll() {
        return serviceTypeRepo.findAll();
    }

    @Override
    public ServiceType findByName(String name) {
        return serviceTypeRepo.findByName(name);
    }

    public Page<ServiceType> findAll(Pageable pageable) {
        return serviceTypeRepo.findAll(pageable);
    }

    public ServiceType getById(Integer id) {
        return serviceTypeRepo.getById(id);
    }

    public <S extends ServiceType> S save(S entity) {
        return serviceTypeRepo.save(entity);
    }

    public ServiceType findId(Integer id) {
        return serviceTypeRepo.findId(id);
    }

    public long count() {
        return serviceTypeRepo.count();
    }

    public void deleteById(Integer id) {
        serviceTypeRepo.deleteById(id);
    }


    @Override
    public ServiceType insert(ServiceTypeDTO serviceTypeDTO) {
        ServiceType serviceType = new ServiceType(serviceTypeDTO.getName(), serviceTypeDTO.getDescription());
        return save(serviceType);

    }

    @Override
    public boolean existsById(Integer integer) {
        return serviceTypeRepo.existsById(integer);
    }


    @Override
    public Optional<ServiceType> findById(Integer integer) {
        return serviceTypeRepo.findById(integer);
    }

    @Override
    public ServiceType edit(ServiceTypeDTO serviceTypeDTO, int id) {
        Optional<ServiceType> serviceTypeData = findById(id);
        if (serviceTypeData.isPresent()) {
            ServiceType serviceType = serviceTypeData.get();
            serviceType.setName(serviceTypeDTO.getName());
            serviceType.setDescription(serviceTypeDTO.getDescription());

            return save(serviceType);
        }
        return null;

    }

    public <S extends ServiceType> List<S> findAll(Example<S> example) {
        return serviceTypeRepo.findAll(example);
    }


}
