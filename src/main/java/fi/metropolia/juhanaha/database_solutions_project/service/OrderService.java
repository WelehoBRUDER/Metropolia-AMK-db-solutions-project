package fi.metropolia.juhanaha.database_solutions_project.service;

import fi.metropolia.juhanaha.database_solutions_project.dto.OrderDto;
import fi.metropolia.juhanaha.database_solutions_project.entity.Order;


public class OrderService {
    public static OrderDto toDTO(Order order) {
        if (order == null) return null;

        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setDeliveryDate(order.getDeliveryDate());
        dto.setShippingAddressId(order.getShippingAddressId());
        dto.setStatus(order.getStatus());
        dto.setOrderItems(order.getOrderItems().stream().map(OrderItemService::toDTO).toList());
        return dto;
    }
}
