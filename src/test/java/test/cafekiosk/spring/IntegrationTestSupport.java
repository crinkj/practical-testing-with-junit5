package test.cafekiosk.spring;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import test.cafekiosk.spring.api.client.MailSendClient;
import test.cafekiosk.spring.api.service.order.OrderService;
import test.cafekiosk.spring.api.service.product.ProductService;

@ActiveProfiles("test")
@SpringBootTest
public abstract class IntegrationTestSupport {
 // mockBean을 사용하는 경우 여기에 명시해줘야한다
    @MockBean
    protected MailSendClient mailSendClient;

}
