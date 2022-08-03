package com.example.duchanhdental.controller;

import com.example.duchanhdental.entity.User;
import com.example.duchanhdental.model.LoginRequest;
import com.example.duchanhdental.model.response.UserResponse;
import com.example.duchanhdental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam String key) throws InterruptedException {
        List<UserResponse> userResponseList = userService.searchUserByFullName(key);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseList);
    }


    @PostMapping("/getUserByPhone")
    public ResponseEntity<?> getUserByPhone(@RequestParam String phoneNumber) {
        List u = new ArrayList();
        int i = Integer.parseInt(phoneNumber);
        u.add(1);
        u.add(2);
        u.add(3);
        u.add(4);
        //User user = userService.getUserByPhone(phoneNumber);
        //return ResponseEntity.status(200).body(user);
        return ResponseEntity.status(200).body(u.get(i));
    }
}
