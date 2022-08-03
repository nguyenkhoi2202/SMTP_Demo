package com.example.duchanhdental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_name", nullable = true, unique = true)
    @Type(type = "org.hibernate.type.TextType")
    private String userName;

    @Column(name = "password", nullable = true)
    @Type(type = "org.hibernate.type.TextType")
    private String password;

    @Column(name = "full_name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String fullName;

    @Column(name = "age")
    private Long age;

    @Column(name = "phone", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String phone;

    @Column(name = "address")
    @Type(type = "org.hibernate.type.TextType")
    private String address;

    @Column(name = "role_id", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String roleId;



}