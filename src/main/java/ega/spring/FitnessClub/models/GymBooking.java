package ega.spring.FitnessClub.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "workout_booking")
@Getter
@Setter
public class GymBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Person user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "deleted")
    private boolean deleted;

    public GymBooking() {}
}
