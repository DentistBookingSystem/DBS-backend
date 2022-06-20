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
public class Appointment implements Serializable, Comparable<Appointment>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = true)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne()
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "appointment_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date appointment_date;

    @Column(name = "appointment_time", nullable = false)
    private String appointment_time;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "time_making", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_making;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<AppointmentDetail> appointmentDetailSet;

    @JsonIgnore
    @OneToOne(mappedBy = "appointment")
    private Feedback feedback;

    public Appointment(Account account, Branch branch, Doctor doctor, Date date, String time, int status, Date time_making){
        this.account = account;
        this.branch = branch;
        this.doctor = doctor;
        this.appointment_date = date;
        this.appointment_time = time;
        this.status = status;
        this.time_making = time_making;
    }

    @Override
    public int compareTo(Appointment appointment) {
        return this.time_making.compareTo(appointment.getTime_making());
    }

}
