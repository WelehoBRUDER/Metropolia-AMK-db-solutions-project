package fi.metropolia.juhanaha.database_solutions_project.dto;

import fi.metropolia.juhanaha.database_solutions_project.converter.OrderStatusConverter;
import fi.metropolia.juhanaha.database_solutions_project.entity.OrderItem;
import fi.metropolia.juhanaha.database_solutions_project.enums.OrderStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDto {
    private Integer id;
    private Integer customerId;
    private Date orderDate;
    private Date deliveryDate;
    private Integer shippingAddressId;
    private OrderStatus status;
    private List<OrderItemDto> orderItems = new ArrayList<OrderItemDto>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Integer shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
