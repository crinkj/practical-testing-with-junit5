package test.cafekiosk.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import test.cafekiosk.spring.api.client.MailSendClient;
import test.cafekiosk.spring.api.controller.order.OrderController;
import test.cafekiosk.spring.api.controller.product.ProductController;
import test.cafekiosk.spring.api.service.order.OrderService;
import test.cafekiosk.spring.api.service.product.ProductService;

@WebMvcTest({OrderController.class, ProductController.class})
public abstract class ControllerTestSupport {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected OrderService orderService;

    @MockBean
    protected ProductService productService;

}
