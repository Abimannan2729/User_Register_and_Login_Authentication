package com.abimannan.user_register_and_login_authentication.service;

import com.abimannan.user_register_and_login_authentication.model.User;


interface UserService{
     User save(User user);
     User findByEmail(String email);
}
