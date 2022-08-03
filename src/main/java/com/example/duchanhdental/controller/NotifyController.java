package com.example.duchanhdental.controller;

import com.example.duchanhdental.firebase.PushNotifycationService;
import com.example.duchanhdental.model.NotifyRequest;
import com.example.duchanhdental.model.response.NotifyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotifyController {
    @Autowired
    PushNotifycationService pushNotifycationService;

    @PostMapping("/token")
    public ResponseEntity<?> sentokenNotifycation(@RequestBody NotifyRequest request){
        pushNotifycationService.sendPushNotificationToToken(request);
        return  new ResponseEntity<>(new NotifyResponse(HttpStatus.OK.value(),"Push notify successful"),HttpStatus.OK);
    }
}
