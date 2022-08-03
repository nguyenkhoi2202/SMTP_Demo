package com.example.duchanhdental.service;

import com.example.duchanhdental.entity.User;
import com.example.duchanhdental.model.LoginRequest;
import com.example.duchanhdental.model.RegisterRequest;
import com.example.duchanhdental.model.response.UserResponse;

import java.util.List;

public interface UserService {
    User getUserByUserName(String userName);
    User getUserByPhone(String phone);

    List<UserResponse> searchUserByFullName(String fullname) throws InterruptedException;

  boolean register(RegisterRequest request);

    boolean createTokenAndChangeStatus(LoginRequest request);
    void logout(String userName);
}
