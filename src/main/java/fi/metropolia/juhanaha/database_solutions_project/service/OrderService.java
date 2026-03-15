package fi.metropolia.juhanaha.database_solutions_project.service;

import fi.metropolia.juhanaha.database_solutions_project.dto.OrderDto;
import fi.metropolia.juhanaha.database_solutions_project.dto.SupplierDto;
import fi.metropolia.juhanaha.database_solutions_project.entity.Order;
import fi.metropolia.juhanaha.database_solutions_project.entity.Supplier;
import fi.metropolia.juhanaha.database_solutions_project.enums.OrderStatus;

import java.util.Date;

public class OrderService {
    public static OrderDto toDTO(Order order) {
        if (order == null) return null;

        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomerId());
        dto.setOrderDate(order.getOrderDate());
        dto.setDeliveryDate(order.getDeliveryDate());
        dto.setShippingAddressId(order.getShippingAddressId());
        dto.setStatus(order.getStatus());
        dto.setOrderItems(order.getOrderItems().stream().map(OrderItemService::toDTO).toList());
        return dto;
    }
}
