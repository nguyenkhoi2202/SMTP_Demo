package com.example.duchanhdental.service.schedule;


import com.example.duchanhdental.entity.User;
import com.example.duchanhdental.firebase.FCMService;
import com.example.duchanhdental.model.response.UserResponse;
import com.example.duchanhdental.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
public class NotifySchedule {
    @Autowired
    UserRepository userRepo;

    @Autowired
    private FCMService fcmService;


    // mỗi 9h sáng hằng ngảy theo giờ việt nam
    @Scheduled(cron = "0 0 9 * * ?", zone = "Asia/Saigon")
    public void scheduleFixedDelayTask() {
        List<User> users = userRepo.findUserByFullName("Kh");
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : users) {
            ModelMapper mapper = new ModelMapper();
            // mapper user to userResponse and add to list
            userResponseList.add(mapper.map(user, UserResponse.class));
            System.out.println("đã tìm ok");
            System.out.println(userResponseList.size());
        }
    }

}
