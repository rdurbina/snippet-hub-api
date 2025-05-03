package com.rdurbina.snippethub.service;

import com.rdurbina.snippethub.dto.user.UserRequest;
import com.rdurbina.snippethub.dto.user.UserResponse;
import com.rdurbina.snippethub.exception.EmailAlreadyInUseException;
import com.rdurbina.snippethub.exception.UsernameAlreadyInUseException;
import com.rdurbina.snippethub.model.User;
import com.rdurbina.snippethub.repository.UserRepository;
import com.rdurbina.snippethub.service.user.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    private User user;

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        user = User
                .builder()
                .username("thisusernameisnotinuse")
                .email("example.email@mail.com")
                .password("MyLongAndSecurePassword")
                .build();
    }

    @Test
    public void register_givenCorrectInput_shouldReturnUserResponse() {
        UserRequest userRequest = UserRequest
                .builder()
                .email("example.email@mail.com")
                .password("MyLongAndSecurePassword")
                .username("thisusernameisnotinuse")
                .build();

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserResponse result = userService.register(userRequest);

        Assertions.assertNotNull(result);
        Assertions.assertInstanceOf(UserResponse.class, result);
    }

    @Test
    public void register_givenInvalidUsername_throwsUsernameAlreadyInUseException() {
        UserRequest userRequest = UserRequest
                .builder()
                .email("example.email@mail.com")
                .password("MyLongAndSecurePassword")
                .username("thisusernameisalreadyinuse")
                .build();

        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);
        when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);

        Assertions.assertThrows(UsernameAlreadyInUseException.class, () -> userService.register(userRequest));
    }

    @Test
    public void register_givenInvalidEmail_throwsEmailAlreadyInUseException() {
        UserRequest userRequest = UserRequest
                .builder()
                .email("example.email@mail.com")
                .password("MyLongAndSecurePassword")
                .username("thisusernameisnotinuse")
                .build();

        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);

        Assertions.assertThrows(EmailAlreadyInUseException.class, () -> userService.register(userRequest));
    }
}
