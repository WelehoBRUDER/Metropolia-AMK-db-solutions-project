package fi.metropolia.juhanaha.database_solutions_project.entity;

import fi.metropolia.juhanaha.database_solutions_project.converter.OrderStatusConverter;
import fi.metropolia.juhanaha.database_solutions_project.enums.OrderStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // Make JPA connection later
    @Column(name="customer_id")
    private int customerId;
    @Column(name="order_date")
    private Date orderDate;
    @Column(name = "delivery_date")
    private Date deliveryDate;
    @Column(name="shipping_address_id")
    private Integer shippingAddressId;
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
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
}
