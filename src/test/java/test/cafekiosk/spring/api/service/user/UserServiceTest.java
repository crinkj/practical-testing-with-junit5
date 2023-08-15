package test.cafekiosk.spring.api.service.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import test.cafekiosk.spring.IntegrationTestSupport;
import test.cafekiosk.spring.api.service.user.request.UserJoinRequest;
import test.cafekiosk.spring.api.service.user.response.UserResponse;
import test.cafekiosk.spring.domain.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest extends IntegrationTestSupport {
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("유저가 회원가입을 한다.")
    void joinUserTest(){
        // given
        UserJoinRequest request = UserJoinRequest.builder()
                .age(15)
                .name("john")
                .nickname("test")
                .build();

        // when
        UserResponse response = userService.joinUser(request);

        // then
        assertThat(response).isNotNull();
        assertThat(response).extracting("age","nickname","name")
                .contains(15,"john","test");
    }

}