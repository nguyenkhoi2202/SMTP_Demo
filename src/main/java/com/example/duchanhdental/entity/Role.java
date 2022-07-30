package com.example.duchanhdental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @Column(name = "role_id", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String id;

    @Column(name = "role_name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String roleName;



}