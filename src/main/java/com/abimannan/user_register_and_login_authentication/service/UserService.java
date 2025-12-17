package com.abimannan.user_register_and_login_authentication.service;

import com.abimannan.user_register_and_login_authentication.model.User;

import java.util.List;
import java.util.Optional;

interface UserService{
     User save(User user);
     User findByEmail(String email);
}
