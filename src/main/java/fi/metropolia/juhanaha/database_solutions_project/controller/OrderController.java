package fi.metropolia.juhanaha.database_solutions_project.controller;

import fi.metropolia.juhanaha.database_solutions_project.CustomerRepository;
import fi.metropolia.juhanaha.database_solutions_project.OrderRepository;
import fi.metropolia.juhanaha.database_solutions_project.entity.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        return orderRepository.findById(id)
                .map(product -> ResponseEntity.ok(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order order) {
        Optional<Order> existingOrder = orderRepository.findById(id);

        if (existingOrder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Order updatedOrder = existingOrder.get();
        updatedOrder.setStatus(order.getStatus());
        updatedOrder.setDeliveryDate(order.getDeliveryDate());

        orderRepository.save(updatedOrder);

        return ResponseEntity.ok(updatedOrder);
    }
}
