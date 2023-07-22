package test.cafekiosk.spring.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.cafekiosk.spring.api.service.product.ProductService;
import test.cafekiosk.spring.api.service.product.response.ProductResponse;

import java.util.List;


@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("selling")
    public List<ProductResponse> getSellingProducts(){
        return productService.getSellingProducts();
    }
}
