package com.abimannan.user_register_and_login_authentication.controller;

import com.abimannan.user_register_and_login_authentication.dto.AuthResponse;
import com.abimannan.user_register_and_login_authentication.dto.LoginRequest;
import com.abimannan.user_register_and_login_authentication.dto.RegisterRequest;
import com.abimannan.user_register_and_login_authentication.model.User;
import com.abimannan.user_register_and_login_authentication.security.JWTUtil;
import com.abimannan.user_register_and_login_authentication.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;


//    public UserController(UserServiceImpl userService, PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtUtil = jwtUtil;
//    }


    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        User  user= new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.save(user);
        return "Register User successful";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        System.out.println("LOGIN API HIT");
        User user = userService.findByEmail(request.getEmail());
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("In Valid Credentials");
        }
        String generatedToken = jwtUtil.generateToken(request.getEmail());
        return new AuthResponse(generatedToken);
    }

    @GetMapping("/profile")
    public String profile(){
        return "Welcome to  profile";
    }

    @GetMapping("/me")
    public User me() {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        user.setPassword(null); // NEVER expose password
        return user;
    }

}
