package ega.spring.FitnessClub.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "spa_booking")
@Getter
@Setter
public class SpaBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Person user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "procedure_id", referencedColumnName = "id")
    private SpaProcedure procedure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private SpaEmployee employee;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "deleted")
    private boolean deleted;

    public SpaBooking() {}

}
