package com.rdurbina.snippethub.mapper;

import com.rdurbina.snippethub.dto.user.UserRequest;
import com.rdurbina.snippethub.dto.user.UserResponse;
import com.rdurbina.snippethub.model.User;

public class UserMapper {
    public static User toUser(UserRequest request) {
        return User.builder()
                .id(request.id())
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
