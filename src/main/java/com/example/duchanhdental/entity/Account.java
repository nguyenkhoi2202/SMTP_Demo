package com.example.duchanhdental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Column(unique = true, name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(unique = true, name = "role_id")
    private String roleId;
}
