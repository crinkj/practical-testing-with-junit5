package test.cafekiosk.spring.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;
    private String name;
    private String nickname;

    private LocalDateTime joinedAt;

    @Builder
    private User(int age, String name, String nickname) {
        this.age = age;
        this.name = name;
        this.nickname = nickname;
    }

    public static User create(int age, String name, String nickname) {
        return User.builder()
                .age(age)
                .name(name)
                .nickname(nickname)
                .build();
    }

}
