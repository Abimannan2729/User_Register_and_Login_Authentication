package com.abimannan.user_register_and_login_authentication.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
