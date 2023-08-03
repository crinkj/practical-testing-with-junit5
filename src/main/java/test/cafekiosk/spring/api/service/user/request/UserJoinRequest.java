package test.cafekiosk.spring.api.service.user.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinRequest {
    private int age;
    private String name;
    private String nickname;

    @Builder
    private UserJoinRequest(int age, String name, String nickname) {
        this.age = age;
        this.name = name;
        this.nickname = nickname;
    }
}
