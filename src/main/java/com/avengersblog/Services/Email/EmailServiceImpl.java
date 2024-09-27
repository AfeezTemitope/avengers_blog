package com.avengersblog.Services.Email;

import com.avengersblog.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    @Value("http://localhost:9002")
    private String host;
    private String fromEmail;
    private final JavaMailSender emailSender;


    @Override
    public void sendSimpleMailMessage(String name, String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText(EmailUtil.getEmailMessage(name,host,token));
            emailSender.send(message);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }

    }



    @Override
    public void sendMineMessageWithAttachments(String name, String to, String token) {

    }

    @Override
    public void sendMineWithEmbeddedImages(String name, String to, String token) {

    }

    @Override
    public void sendMineMessageWithEmbeddedFiles(String name, String to, String token) {

    }

    @Override
    public void sendHtmlEmail(String name, String to, String token) {

    }

    @Override
    public void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token) {

    }
}
