package com.email.Email.sender.App.service.impl;

import com.email.Email.sender.App.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("farheen.ali108@gmail.com");
        mailSender.send(simpleMailMessage);
        logger.info("Email has been sent");
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

       simpleMailMessage.setTo(to);
       simpleMailMessage.setSubject(subject);
       simpleMailMessage.setText(message);
       simpleMailMessage.setFrom("farheen.ali108@gmail.com");
       mailSender.send(simpleMailMessage);
       logger.info("Email has been sent");
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String message) {

        MimeMessage simpleMailMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage,true,"UTF-8");
            helper.setTo(to);
            helper.setFrom("farheen.ali108@gmail.com");
            helper.setSubject(subject);
            helper.setText(message,true);
            mailSender.send(simpleMailMessage);
            logger.info("Email has been sent..");

        }catch (MessagingException e){
            throw new RuntimeException(e);
        }





    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {

        MimeMessage simpleMailMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage,true,"UTF-8");
            helper.setTo(to);
            helper.setFrom("farheen.ali108@gmail.com");
            helper.setSubject(subject);
            helper.setText(message,true);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(),file);
            mailSender.send(simpleMailMessage);
            logger.info("Email has been sent..");

        }catch (MessagingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, InputStream is) {

        MimeMessage simpleMailMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage,true);
            helper.setTo(to);
            helper.setFrom("farheen.ali108@gmail.com");
            helper.setSubject(subject);
            helper.setText(message,true);

            File file = new File("C:\\Users\\INDIA\\Desktop\\Email-Sender-App\\Email sender App\\Email-sender-App\\src\\main\\resources\\email\\test.png");
            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(),file);

           mailSender.send(simpleMailMessage);
            logger.info("Email has been sent..");

        }catch (MessagingException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
