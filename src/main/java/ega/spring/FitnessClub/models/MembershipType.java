package ega.spring.FitnessClub.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "membership_type")
@Getter
@Setter
public class MembershipType {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "gym_visits")
    private int gymVisits;

    @Column(name = "spa_visits")
    private int spaVisits;

    @Column(name = "duration")
    private int duration;

    @Column(name = "price")
    private double price;

    public MembershipType() {}

}
