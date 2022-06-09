package com.rade.dentistbookingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Branch")
public class Branch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "nvarchar(30)", unique = true)
    private String name;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @Column(nullable = false, columnDefinition = "varchar(MAX)")
    private String url;

    @Column(name = "open_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date open_time;

    @Column(name = "close_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date close_time;

    @Column(nullable = false)
    private int status;

    @JsonIgnore
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private Set<Appointment> appointmentSet;
    @JsonIgnore
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private Set<Doctor> doctorSet;

    public Branch(String name, District byId, Date open_time, Date close_time, int status, String url) {
        this.name = name;
        this.district = byId;
        this.url = url;
        this.open_time = open_time;
        this.close_time = close_time;
        this.status = status;
    }
}
