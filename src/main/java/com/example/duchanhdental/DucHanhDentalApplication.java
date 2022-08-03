package com.example.duchanhdental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
public class DucHanhDentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DucHanhDentalApplication.class, args);
    }


}
