package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.GymBooking;
import ega.spring.FitnessClub.models.Order;
import ega.spring.FitnessClub.models.OrderItem;
import ega.spring.FitnessClub.models.Product;
import ega.spring.FitnessClub.repositories.OrderRepository;
import ega.spring.FitnessClub.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public void addProductToOrder(Order order, Product product, int quantity) {
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setPrice(product.getPrice() * quantity);
            order.getOrderItems().add(item);
            order.setTotalPrice(order.getTotal_price() + item.getPrice());
        }

    @Transactional
    public void processOrder(Order order) {
        if (order.getOrderItems().isEmpty()) {
            throw new IllegalStateException("Корзина пуста. Добавьте товары в заказ.");
        }

        order.setDate(new Date());
        order.setStatus("Оформлен");

        if (order.getUser() == null) {
            throw new IllegalStateException("Пользователь не задан для заказа.");
        }

        for (OrderItem item : order.getOrderItems()) {
            item.setOrder(order);
        }

        orderRepository.save(order);

        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            if (product.getStock() < item.getQuantity()) {
                throw new IllegalStateException("Недостаточное количество товара на складе: " + product.getName());
            }
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }
    }

    public List<Order> getUserOrders(int userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllByDeletedFalse();
    }

    public void deleteOrderById(int id) {
        Order order = orderRepository.findById(id);
        if (order != null) {
            order.setDeleted(true);
            orderRepository.save(order);
        }
    }

    public void updateOrder(int id, Order updatedOrder) {
        Order order = orderRepository.findById(id);
        if (order != null) {
            order.setStatus(updatedOrder.getStatus());
            order.setTotalPrice(updatedOrder.getTotal_price());
            orderRepository.save(order);
        }
    }

    public Order getOrderById(int orderId) {
         return orderRepository.findById(orderId);
    }
}
