package com.example.duchanhdental.service.impl;

import com.example.duchanhdental.entity.User;
import com.example.duchanhdental.model.LoginRequest;
import com.example.duchanhdental.model.RegisterRequest;
import com.example.duchanhdental.repository.UserRepository;
import com.example.duchanhdental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Override
    public User getUserByUserName(String userName) {
        User user = null;
        try {
            user = userRepo.findByUsername(userName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean register(RegisterRequest request) {
        boolean flag = false;
        User user = userRepo.getUserByUserName(request.getUsername());
        if(user != null){
            flag = false;
        }else{
            User userRegister = new User();
            userRegister.setUserName(request.getUsername());
            userRegister.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
            userRegister.setFullName(request.getFullname());
            userRegister.setAge(request.getAge());
            userRegister.setPhone(request.getUsername());
            userRegister.setAddress(request.getAddress());
            userRegister.setRoleId("3");

            userRepo.save(userRegister);
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean createTokenAndChangeStatus(LoginRequest request) {
        return false;
    }

    @Override
    public void logout(String userName) {

    }
}
