package test.cafekiosk.spring.api.service.order.response;

import lombok.Builder;
import lombok.Getter;
import test.cafekiosk.spring.api.service.product.response.ProductResponse;
import test.cafekiosk.spring.domain.order.Order;
import test.cafekiosk.spring.domain.order.OrderStatus;
import test.cafekiosk.spring.domain.orderproduct.OrderProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponse {
    private Long id;
    private int totalPrice;
    private LocalDateTime registeredDateTime;
    private List<ProductResponse> products;

    @Builder
    private OrderResponse(Long id, int totalPrice, LocalDateTime registeredDateTime, List<ProductResponse> products) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.products = products;
    }

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .registeredDateTime(order.getRegisteredDateTime())
                .products(
                        order.getOrderProducts().stream()
                                .map(orderProduct -> ProductResponse.of(orderProduct.getProduct()))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
