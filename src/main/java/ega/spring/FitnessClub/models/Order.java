package ega.spring.FitnessClub.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
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
    private double total_price;

    @Column(name = "status")
    private String status = "Обрабатывается";

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "deleted")
    private boolean deleted;

    public Order() {}

    public void setTotalPrice(double totalPrice) {
        this.total_price = totalPrice;
    }


}
