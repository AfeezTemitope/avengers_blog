package com.avengersblog.Services.Email;

public interface EmailService {
    void sendSimpleMailMessage(String name, String to, String token);
    void sendMineMessageWithAttachments  (String name, String to, String token);
    void sendMineWithEmbeddedImages(String name, String to, String token);
    void sendMineMessageWithEmbeddedFiles(String name, String to, String token);
    void sendHtmlEmail(String name, String to, String token);
    void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token);
}
