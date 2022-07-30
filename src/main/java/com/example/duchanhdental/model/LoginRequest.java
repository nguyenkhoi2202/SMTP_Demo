package com.example.duchanhdental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements Serializable {
    private String username;
    private String password;
    private String token;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
