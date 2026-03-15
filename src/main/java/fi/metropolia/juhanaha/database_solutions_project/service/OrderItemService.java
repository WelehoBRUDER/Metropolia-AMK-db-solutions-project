package fi.metropolia.juhanaha.database_solutions_project.service;

import fi.metropolia.juhanaha.database_solutions_project.dto.OrderItemDto;
import fi.metropolia.juhanaha.database_solutions_project.entity.OrderItem;

public class OrderItemService {
    public static OrderItemDto toDTO(OrderItem orderItem) {
        if (orderItem == null) return null;

        OrderItemDto dto = new OrderItemDto();
        dto.setOrder(orderItem.getOrder().getId());
        dto.setProduct(ProductService.toSimpleDTO((orderItem.getProduct())));
        dto.setQuantity(orderItem.getQuantity());
        dto.setUnitPrice(orderItem.getUnitPrice());
        return dto;
    }
}
