package com.rade.dentistbookingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Service")
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "service_type_id", nullable = false)
    private ServiceType serviceType;

    @Column(name = "name", nullable = false, columnDefinition = "nvarchar(50)", unique = true)
    private String name;

    @Column(name = "url", nullable = false, columnDefinition = "varchar(MAX)")
    private String url;

    @Column(name = "description", nullable = false, columnDefinition = "nvarchar(MAX)")
    private String description;

    @Column(name = "status", nullable = false)
    private short status;

    @Column(name = "min_price", nullable = false)
    private float minPrice;

    @Column(name = "max_price", nullable = false)
    private float maxPrice;

    @Column(name = "estimated_time", nullable = false)
    private float estimatedTime;

    // Discount service
    @OneToMany(mappedBy = "service")
    @JsonIgnore
    private Set<DiscountService> discountServiceSet;


    public Service(ServiceType byId, String name, String url, String description, short status, float minPrice, float maxPrice) {
        this.serviceType = byId;
        this.name = name;
        this.url = url;
        this.description = description;
        this.status = status;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
