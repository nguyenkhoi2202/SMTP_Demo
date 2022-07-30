package com.example.duchanhdental.service;

import com.example.duchanhdental.entity.User;
import com.example.duchanhdental.model.LoginRequest;
import com.example.duchanhdental.model.RegisterRequest;

public interface UserService {
    User getUserByUserName(String userName);

    boolean register(RegisterRequest request);

    boolean createTokenAndChangeStatus(LoginRequest request);
    void logout(String userName);
}
