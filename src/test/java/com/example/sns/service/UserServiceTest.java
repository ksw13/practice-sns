package com.example.sns.service;

import com.example.sns.exception.SnsApplicationException;
import com.example.sns.fixture.UserEntityFixture;
import com.example.sns.model.entity.UserEntity;
import com.example.sns.repository.UserEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootApplication
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserEntityRepository userEntityRepository;

    @MockBean
    private BCryptPasswordEncoder encoder;

    @Test
    void 회원가입이_정상적으로_동작() {
        String userName = "userName";
        String password = "password";

        // mocking
        // 회원가입이 된적이 없기 때문에 empty() 반환
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());
        when(encoder.encode(password)).thenReturn("encrypt_password");
        when(userEntityRepository.save(any())).thenReturn(Optional.of(mock(UserEntity.class)));

        Assertions.assertDoesNotThrow(() -> userService.join(userName,password));
    }

    @Test
    void 회원가입시_userName으로_회원가입한적이_있는경우() {
        String userName = "userName";
        String password = "password";

        // mocking
        // 회원가입이 된적이 없기 때문에 empty() 반환
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(mock(UserEntity.class)));
        when(encoder.encode(password)).thenReturn("encrypt_password");
        when(userEntityRepository.save(any())).thenReturn(Optional.of(mock(UserEntity.class)));

        SnsApplicationException e = Assertions.assertThrows(SnsApplicationException.class, () -> userService.join(userName, password));

    }

    @Test
    void 로그인이_정상적으로_동작하는_경우() {
        String userName = "userName";
        String password = "password";

        // Fixture로 가짜 entity 생성
        UserEntity fixture = UserEntityFixture.get(userName, password);
        // mocking
        // 회원가입이 된적이 없기 때문에 empty() 반환
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));

        Assertions.assertDoesNotThrow(()-> userService.login(userName,password));
    }

    @Test
    void 로그인시_userName으로_회원가입한_유저가_없는경우() {
        String userName = "userName";
        String password = "password";

        // mocking
        // 회원가입이 된적이 없기 때문에 empty() 반환
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());

        Assertions.assertDoesNotThrow(()-> userService.login(userName,password));
    }

    @Test
    void 로그인시_password가_틀린경우() {
        String userName = "userName";
        String password = "password";
        String wrongPassword = "wrong";

        UserEntity fixture = UserEntityFixture.get(userName, password);

        // mocking
        // 회원가입이 된적이 없기 때문에 empty() 반환
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));

        Assertions.assertThrows(SnsApplicationException.class, ()-> userService.login(userName,wrongPassword));
    }
}
