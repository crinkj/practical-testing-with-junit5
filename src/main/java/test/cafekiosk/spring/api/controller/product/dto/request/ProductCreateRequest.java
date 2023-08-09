package test.cafekiosk.spring.api.controller.product.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import test.cafekiosk.spring.domain.product.Product;
import test.cafekiosk.spring.domain.product.ProductSellingStatus;
import test.cafekiosk.spring.domain.product.ProductType;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {
    /**
     * @NotBlank null도안되고 공백도 안된다. 무조건 문자가 있어야한다.
     * @NotNull  "", " " 통과 null만안됨
     * @NotEmpty " " 은 통과
     */
    @Positive(message = "상품 가격은 양수여야 합니다." )
    private int price;

    // String name -> 상품 이름은 20자 제한
    @NotBlank(message = "상품 이름은 필수입니다.")
    private String name;
    @NotNull(message = "상품 타입은 필수입니다.")
    private ProductType type;
    @NotNull(message = "상품 판매상태는 필수입니다.")
    private ProductSellingStatus sellingStatus;
    @Builder
    public ProductCreateRequest(int price, String name, ProductType type, ProductSellingStatus sellingStatus) {
        this.price = price;
        this.name = name;
        this.type = type;
        this.sellingStatus = sellingStatus;
    }

    public Product toEntity(String nextProductNumber) {
        return Product.builder()
                .productNumber(nextProductNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}
