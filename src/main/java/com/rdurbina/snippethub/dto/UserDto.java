package com.rdurbina.snippethub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDto(
        Long id,
        @NotBlank(message = "The username can't be blank.")
        @Pattern(
                regexp = "^[a-zA-Z0-9]{2,20}$",
                message = "Value must be 2–20 characters long and contain only letters and digits."
        )
        String username,
        @Email(message = "Please, enter a valid email address.")
        String email,
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*\\d).{6,}$",
                message = "Password must be at least 6 characters long, contain a capital letter and a at least one " +
                        "number."
        )
        String password
) {}
