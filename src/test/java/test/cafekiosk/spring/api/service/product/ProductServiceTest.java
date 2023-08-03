package test.cafekiosk.spring.api.service.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import test.cafekiosk.spring.domain.product.Product;
import test.cafekiosk.spring.domain.product.ProductRepository;
import test.cafekiosk.spring.domain.product.ProductSellingStatus;
import test.cafekiosk.spring.domain.product.ProductType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static test.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static test.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Test
    @DisplayName("신규 상품을 등록하낟. 상품번호는 가장 최근 상품의 상품번호에서 1 증가한 값이다.")
    void createProduct(){
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
        Product product3 = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
        productRepository.saveAll(List.of(product1,product2,product3));

        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();

        // when
        productService.createProduct(request);

        // then
    }
    
    @Test
    @DisplayName("")
    void ProductServiceTest(){
        // given

        // when
        
        // then
    }

    private Product createProduct(String productNumber, ProductType handmade, ProductSellingStatus status, String name, int price) {
        Product product = Product.builder()
                .productNumber(productNumber)
                .type(handmade)
                .sellingStatus(status)
                .name(name)
                .price(price)
                .build();
        return product;
    }
}