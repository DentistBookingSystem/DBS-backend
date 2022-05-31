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
@Table(name = "ServiceType")
public class ServiceType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, columnDefinition = "nvarchar(30)", unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "nvarchar(MAX)")
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "serviceType", cascade = CascadeType.ALL)
    private Set<Service> serviceSet;

    public ServiceType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
