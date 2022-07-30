package com.example.duchanhdental.repository;


import com.example.duchanhdental.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {


    @Query("select u from User u where u.userName = ?1")
    User findByUsername(String userName);


    User getUserByUserName(String userName);

}
