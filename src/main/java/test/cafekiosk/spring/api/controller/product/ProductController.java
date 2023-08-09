package test.cafekiosk.spring.api.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import test.cafekiosk.spring.api.client.ApiResponse;
import test.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import test.cafekiosk.spring.api.service.product.ProductService;
import test.cafekiosk.spring.api.service.product.response.ProductResponse;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/new")
    public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        return ApiResponse.ok(productService.createProduct(request));
    }

    @GetMapping("/selling")
    public ApiResponse<List<ProductResponse>> getSellingProducts() {
        return ApiResponse.ok(productService.getSellingProducts());
    }
}
