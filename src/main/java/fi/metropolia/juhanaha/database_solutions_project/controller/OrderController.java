package fi.metropolia.juhanaha.database_solutions_project.controller;

import fi.metropolia.juhanaha.database_solutions_project.CustomerRepository;
import fi.metropolia.juhanaha.database_solutions_project.OrderRepository;
import fi.metropolia.juhanaha.database_solutions_project.dto.OrderDto;
import fi.metropolia.juhanaha.database_solutions_project.entity.Order;
import fi.metropolia.juhanaha.database_solutions_project.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/")
    public List<OrderDto> getOrders() {
        return orderRepository.findAll().stream().map(OrderService::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable int id) {
        return orderRepository.findById(id)
                .map(OrderService::toDTO)
                .map(order -> ResponseEntity.ok(order))
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
