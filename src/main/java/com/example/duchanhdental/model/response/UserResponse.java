package com.example.duchanhdental.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String fullname;
    private Long age;
    private String address;
    private String phone;
}
