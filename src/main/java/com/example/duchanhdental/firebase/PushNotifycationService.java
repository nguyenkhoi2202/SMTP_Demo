package com.example.duchanhdental.firebase;

import com.example.duchanhdental.model.NotifyRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PushNotifycationService {

    Logger logger = Logger.getLogger(PushNotifycationService.class);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date ;

    @Autowired
    private FCMService fcmService;

    public void sendPushNotificationToToken(NotifyRequest request) {
        try {
            fcmService.sendMessageToToken(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
