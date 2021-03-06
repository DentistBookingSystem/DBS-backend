package com.rade.dentistbookingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "District")
public class District implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "province_id")
    private Province province;

    @Column(nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Account> accountSet;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Branch> branchSet;
}
