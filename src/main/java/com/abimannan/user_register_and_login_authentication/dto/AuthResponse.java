package com.abimannan.user_register_and_login_authentication.dto;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String token;
}
