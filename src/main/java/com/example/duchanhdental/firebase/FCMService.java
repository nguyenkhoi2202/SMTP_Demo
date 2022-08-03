package com.example.duchanhdental.firebase;

import com.example.duchanhdental.model.NotifyRequest;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FCMService {

    Logger logger = Logger.getLogger(FCMService.class);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date ;

    public void sendMessageToToken(NotifyRequest request)
            throws InterruptedException, ExecutionException, FirebaseMessagingException {
        MulticastMessage message = getPreconfiguredMessageToToken(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        List<String> strings = new ArrayList<>();
        FirebaseMessaging.getInstance().sendMulticast(message);

        logger.info("Sent message to token. Device token: " + request.getToken() + ", " + " msg "+jsonOutput);
    }
    private MulticastMessage getPreconfiguredMessageToToken(NotifyRequest request) {


        return getPreconfiguredMessageBuilder(request).addAllTokens(request.getToken())
                .build();

    }
    private AndroidConfig getAndroidConfig(String token) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(token)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setTag(token).build()).build();
    }
    private ApnsConfig getApnsConfig(String token) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(token).setThreadId(token).build()).build();
    }
    private MulticastMessage.Builder getPreconfiguredMessageBuilder(NotifyRequest request) {
        List<String> registrationTokens = new ArrayList<>();
        ApnsConfig apnsConfig = null;
        AndroidConfig androidConfig = null;
        for (int i = 0; i < request.getToken().size(); i++) {
            registrationTokens.add(request.getToken().get(i));
            androidConfig = getAndroidConfig(request.getToken().get(i));
            apnsConfig = getApnsConfig(request.getToken().get(i));
        }
        return MulticastMessage.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                        new Notification(request.getTitle(), request.getMessage()));
    }
}
