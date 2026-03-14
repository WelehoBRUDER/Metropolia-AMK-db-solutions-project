package fi.metropolia.juhanaha.database_solutions_project.converter;

import fi.metropolia.juhanaha.database_solutions_project.enums.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        if (orderStatus == null) {
            return "CANCELLED";
        }
        return switch (orderStatus) {
            case NEW -> "NEW";
            case SHIPPING -> "SHIPPING";
            default -> "CANCELLED";
        };
    }

    @Override
    public OrderStatus convertToEntityAttribute(String orderStatus) {
        System.out.println("Converting " + orderStatus + " --> enum value");
        if (orderStatus == null) {
            return OrderStatus.CANCELLED;
        }
        return switch (orderStatus) {
            case "NEW" -> OrderStatus.NEW;
            case "SHIPPING" -> OrderStatus.SHIPPING;
            default -> OrderStatus.CANCELLED;
        };
    }
}
