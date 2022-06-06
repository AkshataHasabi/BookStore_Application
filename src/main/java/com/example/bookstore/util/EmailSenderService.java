package com.example.bookstore.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * this annotation will be auto detected using path scanning
 */
@Component
public class EmailSenderService {
    /**
     * it is interface that extends the mailsender.
     * mailsender it will provides functionality for sending simple mails.
     * javamailsender is used for sendind messages.
     *
     */
    @Autowired
    private JavaMailSender mailsender;

    /**
     * SimpleMailMessage it is used to create a simple mail message including toemail,subject,body.
     * @param toEmail=to whom u r sending
     * @param subject=subject
     * @param body=body of the mail
     */
    public void sendEmail(String toEmail, String subject, String body ) {
        //for creating message.
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("akshuh818@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailsender.send(message);
        System.out.println("Mail sent to the User...!");
    }

}
