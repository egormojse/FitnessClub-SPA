package ega.spring.FitnessClub.repositories;

import ega.spring.FitnessClub.models.Order;
import ega.spring.FitnessClub.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(int userId);

    Order findById(int orderId);

    List<Order> findAllByDeletedFalse();
}
