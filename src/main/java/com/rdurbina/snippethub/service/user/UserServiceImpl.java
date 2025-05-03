package com.rdurbina.snippethub.service.user;

import com.rdurbina.snippethub.dto.user.UserRequest;
import com.rdurbina.snippethub.dto.user.UserResponse;
import com.rdurbina.snippethub.exception.EmailAlreadyInUseException;
import com.rdurbina.snippethub.exception.UsernameAlreadyInUseException;
import com.rdurbina.snippethub.mapper.UserMapper;
import com.rdurbina.snippethub.model.User;
import com.rdurbina.snippethub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public UserResponse register(UserRequest userRequest) {
        boolean isEmailAlreadyInUse = userRepository.findByEmail(userRequest.email()) != null;
        if (isEmailAlreadyInUse) throw new EmailAlreadyInUseException("The email is already in use.");
        boolean isUsernameAlreadyInUse = userRepository.findByUsername(userRequest.username()) != null;
        if (isUsernameAlreadyInUse) throw new UsernameAlreadyInUseException("The username is already in use.");
        User user = UserMapper.toUser(userRequest);
        User persistedUser = userRepository.save(user);
        return UserMapper.toResponse(persistedUser);
    }
}
