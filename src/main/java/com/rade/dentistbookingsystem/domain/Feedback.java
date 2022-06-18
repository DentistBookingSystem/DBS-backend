package com.rade.dentistbookingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Feedback")
public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date time;

    @Column(nullable = false, columnDefinition = "nvarchar(MAX)")
    private String content;

    @Column(nullable = false)
    private int status;

    public Feedback(Appointment appointment, Date time, String content, int status){
        this.appointment = appointment;
        this.time = time;
        this.content = content;
        this.status = status;
    }
}
