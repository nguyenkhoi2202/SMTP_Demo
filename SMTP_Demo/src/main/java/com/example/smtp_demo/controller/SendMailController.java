package com.example.smtp_demo.controller;


import com.example.smtp_demo.config.MyConstants;
import com.example.smtp_demo.model.MailSendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@RestController
public class SendMailController {

    @Autowired
    public JavaMailSender emailSender;

    @PostMapping("/saveFileMessage")
    public ResponseEntity<?> saveFileMessage(@RequestBody MailSendRequest request) throws MessagingException {
        String message = request.getMail();
        try {
            FileWriter writer = new FileWriter("D:\\MyFile.txt", true);
            writer.write(message);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Write file success");
    }
    @PostMapping("/readFileMessage")
    public ResponseEntity<?> readFileMessage() throws MessagingException {
        // String message = request.getMail();
        String result = "";
        try {
            FileReader reader = new FileReader("D:\\MyFile.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/sendSimpleEmail")
    public ResponseEntity<?> sendSimpleEmail(@RequestBody MailSendRequest request) throws MessagingException {

        int rd = (int) ((Math.random()*((999999-100000)+1))+100000);
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage mimeMessage = null;
        MimeMessageHelper helper = new MimeMessageHelper( mimeMessage, true );
        helper.setFrom(MyConstants.MY_EMAIL);
        helper.setTo(request.getMail());
        helper.setSubject("Xác thực đăng kí tài khoản tại Nha Khoa Đức Hạnh");
        helper.setText("Mã xác thực otp của bạn là : " + rd +"\n"+
                "Vui lòng nhập vào để xác thực nhé !!! \n" +
                " <!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <img src=\"https://thietkelogodep.com.vn/wp-content/uploads/2017/12/thiet-ke-logo.png\" alt=\"Dentist\" width=\"500\" height=\"600\"> \n" +
                "</body>\n" +
                "</html>" +

                " ");


        // message.setFrom(MyConstants.MY_EMAIL);
//        message.setTo(request.getMail());
//        message.setSubject("Xác thực đăng kí tài khoản tại Nha Khoa Đức Hạnh");
//        message.setText("Mã xác thực otp của bạn là : " + rd +"\n"+
//                "Vui lòng nhập vào để xác thực nhé !!! \n" +
//                " <!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                "    <title>Document</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "  <img src=\"https://thietkelogodep.com.vn/wp-content/uploads/2017/12/thiet-ke-logo.png\" alt=\"Dentist\" width=\"500\" height=\"600\"> \n" +
//                "</body>\n" +
//                "</html>" +
//
//                " ");

        // Send Message!
        this.emailSender.send((MimeMessagePreparator) helper);

        return ResponseEntity.ok(message);
    }

}
