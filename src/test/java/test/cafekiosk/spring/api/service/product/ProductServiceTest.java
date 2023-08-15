package test.cafekiosk.spring.api.service.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import test.cafekiosk.spring.IntegrationTestSupport;
import test.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import test.cafekiosk.spring.api.service.product.response.ProductResponse;
import test.cafekiosk.spring.domain.product.Product;
import test.cafekiosk.spring.domain.product.ProductRepository;
import test.cafekiosk.spring.domain.product.ProductSellingStatus;
import test.cafekiosk.spring.domain.product.ProductType;

import static test.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static test.cafekiosk.spring.domain.product.ProductType.HANDMADE;

class ProductServiceTest extends IntegrationTestSupport {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeAll
    static void beforeALl() {
        // before class
    }

    @BeforeEach
    void setUp() {
        // before method

        // 각 테스트 입장에서 봤을 때 : 아예 몰라도 테스트 내용을 이해하는 데에 문제가 없는가?
        // 수정해도 모든 테스트에 영향을 주지 않는가?

    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("신규 상품을 등록한다. 상품번호는 가장 최근 상품의 상품번호에서 1 증가한 값이다.")
    void createProduct() {
        // given
        Product product1 = createProduct("002", HANDMADE, SELLING, "카푸치노", 5000);
        productRepository.save(product1);

        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();

        // when
        ProductResponse productResponse = productService.createProduct(request);

        // then
        Assertions.assertThat(productResponse)
                .extracting("productNumber", "type", "sellingStatus", "price", "name")
                .contains("003", HANDMADE, SELLING, "카푸치노", 5000);
    }

    @Test
    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001이다.")
    void createProductWhenProductIsEmpty() {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();

        // when
        ProductResponse productResponse = productService.createProduct(request);

        // then
        Assertions.assertThat(productResponse)
                .extracting("productNumber", "type", "sellingStatus", "price", "name")
                .contains("001", HANDMADE, SELLING, "카푸치노", 5000);
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