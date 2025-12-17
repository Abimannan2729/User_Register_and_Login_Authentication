package com.abimannan.user_register_and_login_authentication.service;

import com.abimannan.user_register_and_login_authentication.repository.UserRepository;
import com.abimannan.user_register_and_login_authentication.model.User;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo){
        this.userRepo=userRepo;
    }
    @Override
    public User save(User user) {
        return userRepo.save(user);
    }



    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("No User Found"));
    }


}
