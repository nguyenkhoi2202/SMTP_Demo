package com.example.duchanhdental.controller;

import com.example.duchanhdental.model.LoginRequest;
import com.example.duchanhdental.model.RegisterRequest;
import com.example.duchanhdental.model.response.MessageResponse;
import com.example.duchanhdental.security.JwtTokenUtil;
import com.example.duchanhdental.service.UserService;
import com.example.duchanhdental.service.impl.JwtUserDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUserDetailsService userDetailsService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService jwtUserDetailsService;


    private static Logger logger = Logger.getLogger(AuthenController.class);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        Map<String, Object> responseMap = new HashMap<>();
//        String password  = "111111";
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String encode = bCryptPasswordEncoder.encode(password);

        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                    request.getPassword()));
            if (auth.isAuthenticated()) {
                //create token when login for notify
                boolean check = userService.createTokenAndChangeStatus(request);
                UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
                String token = jwtTokenUtil.generateToken(userDetails);
                responseMap.put("error", false);
                if(!check) {
                    responseMap.put("message", "Logged In");
                    responseMap.put("token", token);
                    logger.debug("Login successful with account " + request.getUsername() + " at:  " + simpleDateFormat.format(new Date()));
                }else {

                    responseMap.put("message", "Logged In but user logged before");
                    responseMap.put("token", token);
                    logger.debug("Login successful with account " + request.getUsername() + " " +
                            " and logged before at:  " + simpleDateFormat.format(new Date()));
                }
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", true);
                responseMap.put("message", "Invalid Credentials");

                logger.debug("Login fail Invalid Credentials with account " + request.getUsername()
                        + " at:  " + simpleDateFormat.format(new Date()));
                return ResponseEntity.status(401).body(responseMap);
            }
        } catch (DisabledException e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "User is disabled");

            logger.debug("Login fail User is disabled with account " + request.getUsername()
                    + " at:  " + simpleDateFormat.format(new Date()));
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            responseMap.put("error", true);
            responseMap.put("message", "Invalid Credentials");

            logger.debug("Login fail Invalid Credentials with account " + request.getUsername()
                    + " at:  " + simpleDateFormat.format(new Date()));
            return ResponseEntity.status(401).body(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "Something went wrong");
            logger.debug("Login fail Something went wrong with account " + request.getUsername()
                    + " at:  " + simpleDateFormat.format(new Date()));
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        boolean register = userService.register(request);

        if(register){
            Map<String, Object> responseMap = new HashMap<>();
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtTokenUtil.generateToken(userDetails);
            responseMap.put("error", false);
            responseMap.put("username", request.getUsername());
            responseMap.put("message", "Account created successfully");
            responseMap.put("token", token);
            logger.debug("Register account " + request.getUsername() + " successful at:  " + simpleDateFormat.format(new Date()));
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        }else{
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setErrorType("Register");
            messageResponse.setStatus("FAIL");
            messageResponse.setMessage("Account exist before");
            logger.debug("Register account " + request.getUsername() + " fail at:  " + simpleDateFormat.format(new Date()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageResponse);
        }
    }


}
