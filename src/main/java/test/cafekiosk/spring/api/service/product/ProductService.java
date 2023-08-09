package test.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import test.cafekiosk.spring.api.service.product.response.ProductResponse;
import test.cafekiosk.spring.domain.product.Product;
import test.cafekiosk.spring.domain.product.ProductRepository;
import test.cafekiosk.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * readonly = true : 읽기전용
 * CRUD 에서 CUD 동작 X / only Read
 * JPA: CUD 스냅샷 저장, 변경감지 x (성능 향상)
 *
 * CQRS - Command / Query
 */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 동시성 이슈: 관라자가 한번에 상품을 등록할때 중복 productNumber가 생길수 있다.
    //            그런 경우 동시성 제어를 해주던지 정책을 변경해서 UUID를 적용할 수 있다.
    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        String nextProductNumber = createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProduct();
        if (latestProductNumber == null) {
            return "001";
        }
        Integer latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }


    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
}
