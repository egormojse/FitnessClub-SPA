package ega.spring.FitnessClub.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "order")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Person user;

    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "total_price")
    private int total_price;

    @Column(name = "status")
    private String status = "Обрабатывается";

    public Order() {}


}
