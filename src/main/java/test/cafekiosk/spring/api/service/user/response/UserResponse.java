package test.cafekiosk.spring.api.service.user.response;

import lombok.Builder;
import lombok.Getter;
import test.cafekiosk.spring.domain.user.User;

import java.time.LocalDateTime;

@Getter
public class UserResponse {
    private final Long id;
    private final int age;
    private final String name;
    private final String nickname;
    private final LocalDateTime joinedAt;

    @Builder
    private UserResponse(Long id,int age,String name,String nickname,LocalDateTime joinedAt){
        this.id = id;
        this.age = age;
        this.name = name;
        this.nickname = nickname;
        this.joinedAt = joinedAt;
    }

    public static UserResponse of(User user){
        return UserResponse.builder()
                .id(user.getId())
                .age(user.getAge())
                .name(user.getName())
                .nickname(user.getNickname())
                .joinedAt(user.getJoinedAt())
                .build();
    }
}
