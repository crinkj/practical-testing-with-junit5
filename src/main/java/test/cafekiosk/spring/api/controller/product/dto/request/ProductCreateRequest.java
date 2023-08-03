package test.cafekiosk.spring.api.controller.product.dto.request;

import lombok.Builder;
import lombok.Getter;
import test.cafekiosk.spring.domain.product.ProductSellingStatus;
import test.cafekiosk.spring.domain.product.ProductType;

@Getter
public class ProductCreateRequest {
    private int price;
    private String name;
    private ProductType type;
    private ProductSellingStatus sellingStatus;
    @Builder
    public ProductCreateRequest(int price, String name, ProductType type, ProductSellingStatus sellingStatus) {
        this.price = price;
        this.name = name;
        this.type = type;
        this.sellingStatus = sellingStatus;
    }
}
