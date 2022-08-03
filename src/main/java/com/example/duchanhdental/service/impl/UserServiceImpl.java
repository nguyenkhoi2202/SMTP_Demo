package com.example.duchanhdental.service.impl;

import com.example.duchanhdental.entity.User;
import com.example.duchanhdental.model.LoginRequest;
import com.example.duchanhdental.model.RegisterRequest;
import com.example.duchanhdental.model.response.UserResponse;
import com.example.duchanhdental.repository.UserRepository;
import com.example.duchanhdental.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public User getUserByPhone(String phone) {
        return userRepo.getUserByPhone(phone);
    }

    @Override
    public List<UserResponse> searchUserByFullName(String fullname) throws InterruptedException {

        List<User> users = userRepo.findUserByFullName(fullname);
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : users) {
            ModelMapper mapper = new ModelMapper();
            // mapper user to userResponse and add to list
            userResponseList.add(mapper.map(user, UserResponse.class));
        }
        return userResponseList;
    }
    public List<UserResponse>  ahihi(String fullname){
        List<User> users = userRepo.findUserByFullName(fullname);
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : users) {
            ModelMapper mapper = new ModelMapper();
            // mapper user to userResponse and add to list
            userResponseList.add(mapper.map(user, UserResponse.class));
        }
        return userResponseList;
    }

    @Override
    public boolean register(RegisterRequest request) {
        boolean flag = false;
        User user = userRepo.getUserByUserName(request.getUsername());
        User userp = userRepo.getUserByPhone(request.getUsername());
        if(userp != null){
            if(user == null){
                //co so dt nhung chua co tai khoan
                User userRegister = new User();
                userp.setUserName(request.getUsername());
                userp.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
                //userRegister.setRoleId("3");
                userRepo.save(userp);
                flag = true;
            }else{
                // co so dt và co tai khoan lun - tai khoan da ton tai
                flag = false;
            }

        }else{
            //tai khoan chua ton tai
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
