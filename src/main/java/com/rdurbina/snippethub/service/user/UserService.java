package com.rdurbina.snippethub.service.user;

import com.rdurbina.snippethub.dto.user.UserRequest;
import com.rdurbina.snippethub.dto.user.UserResponse;

public interface UserService {
    UserResponse register(UserRequest userRequest);
}
