package com.rade.dentistbookingsystem.repository;

import com.rade.dentistbookingsystem.domain.Service;
import com.rade.dentistbookingsystem.domain.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepo extends JpaRepository<Service, Integer> {
    public List<Service> findByServiceType(ServiceType serviceType);

    public List<Service> findByServiceTypeId(int id);

    @Query(value = "SELECT * FROM Service WHERE id = ?1", nativeQuery = true)
    Service findId(Integer id);

    //final String SERVICE_COMPONENT_QUERY = "select Service.id, Service.name, Service.description, Service.min_price, Service.max_price, Service.service_type_id, Service.url, Service.status ,ServiceType.name, ServiceType.description from Service  inner join ServiceType on Service.service_type_id = ServiceType.id" ;

    @Query(value = "SELECT s, s.serviceType from  Service  s join s.serviceType")
    List<Object> getServiceComponentList();


    @Query(value = "SELECT * FROM Service WHERE id = ?1", nativeQuery = true)
    List<Service> findByName(String name);


}
