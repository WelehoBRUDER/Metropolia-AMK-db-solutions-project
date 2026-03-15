package fi.metropolia.juhanaha.database_solutions_project.controller;

import fi.metropolia.juhanaha.database_solutions_project.CustomerRepository;
import fi.metropolia.juhanaha.database_solutions_project.OrderRepository;
import fi.metropolia.juhanaha.database_solutions_project.ProductRepository;
import fi.metropolia.juhanaha.database_solutions_project.dto.OrderDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.OrderItemRequestDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.OrderRequestDto;
import fi.metropolia.juhanaha.database_solutions_project.entity.Order;
import fi.metropolia.juhanaha.database_solutions_project.entity.OrderItem;
import fi.metropolia.juhanaha.database_solutions_project.entity.Product;
import fi.metropolia.juhanaha.database_solutions_project.enums.OrderStatus;
import fi.metropolia.juhanaha.database_solutions_project.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public Order createOrderFromRequest(OrderRequestDto dto) {
        Order order = new Order();

        order.setCustomer(customerRepository.findById(dto.getCustomerId()).orElseThrow());
        order.setShippingAddressId(dto.getShippingAddressId());

        order.setOrderDate(LocalDate.now());
        order.setDeliveryDate(null);
        order.setStatus(OrderStatus.NEW);

        for (OrderItemRequestDto item : dto.getOrderItems()) {

            Product product = productRepository.findById(item.getProductId()).orElseThrow();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(product.getPrice());

            order.addItem(orderItem);
        }
        return orderRepository.save(order);
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

    @PostMapping("/")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequestDto request) {
        Order order = createOrderFromRequest(request);

        return ResponseEntity.ok(OrderService.toDTO(order));
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable int id) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isEmpty()) {
            return ResponseEntity.notFound().build();

        }
        orderRepository.delete(existingOrder.get());
        return ResponseEntity.ok(existingOrder.get());
    }

}
