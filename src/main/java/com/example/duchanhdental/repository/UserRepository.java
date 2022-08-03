package com.example.duchanhdental.repository;


import com.example.duchanhdental.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {


    @Query("select u from User u where u.userName = ?1")
    User findByUsername(String userName);
    @Query("select u from User u where u.fullName like %:key%")
    List<User> findUserByFullName(@Param("key") String key);

    @Query("select u.age from User u where u.fullName like %:key%")
    Long getByAge(@Param("key") String fullname);

    User getUserByUserName(String userName);

    User getUserByPhone(String phoneNumber);

}
