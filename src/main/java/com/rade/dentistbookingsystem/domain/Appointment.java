package com.rade.dentistbookingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Appointment")
public class Appointment implements Serializable, Comparable<Appointment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id", nullable = true)
    private Account account;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @Column(name = "guest_name", columnDefinition = "nvarchar(30)")
    private String guest_name;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "appointment_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "appointment_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date time;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "time_making", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_making;

    @JsonIgnore
    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Set<AppointmentDetail> appointmentDetailSet;

    public Appointment(Account account, Branch branch, String guest_name, String phone, Date date, Date time, int status, Date time_making) {
        this.account = account;
        this.branch = branch;
        this.guest_name = guest_name;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.status = status;
        this.time_making = time_making;
    }

    @Override
    public int compareTo(Appointment appointment) {
        return this.time_making.compareTo(appointment.getTime_making());
    }

}
