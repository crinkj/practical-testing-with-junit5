package test.cafekiosk.spring.api.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.cafekiosk.spring.api.service.user.request.UserJoinRequest;
import test.cafekiosk.spring.api.service.user.response.UserResponse;
import test.cafekiosk.spring.domain.user.User;
import test.cafekiosk.spring.domain.user.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse joinUser(UserJoinRequest request) {
        validateUserRequest(request);

        User user = createUserFromRequest(request);
        User savedUser = userRepository.save(user);

        return UserResponse.of(savedUser);
    }

    private void validateUserRequest(UserJoinRequest request) {
        userRepository.findByNickname(request.getNickname())
                .ifPresent(i -> {
                    throw new IllegalStateException("이미 존재하는 닉네임입니다.");
                });

        if (userIsUnder15(request.getAge())) {
            throw new IllegalArgumentException("나이가 15살 이상이어야합니다.");
        }
    }

    private User createUserFromRequest(UserJoinRequest request) {
        return User.create(request.getAge(), request.getName(), request.getNickname());
    }

    private boolean userIsUnder15(int age) {
        return age < 15;
    }
}
