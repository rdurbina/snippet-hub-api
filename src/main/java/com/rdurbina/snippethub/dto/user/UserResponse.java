package com.rdurbina.snippethub.dto.user;

public record UserResponse(
        Long id,
        String username,
        String email
) {
}
