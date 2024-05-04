package com.email.Email.sender.App.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
public class EmailSenderTest {
    @Autowired
    private EmailService emailService;

    @Test
    void emailSendTest(){
        System.out.println("Sending email");
        emailService.sendEmail("istakarhussain1992@gmail.com",
                "Email from my EmailSender App","This is a test mail");
    }

    @Test
    void sendHtmlInMail(){
        System.out.println("Sending email");
        String html = ""+
                "<h1 style= 'color:red;border:1px solid red;'> Welcome to email sender App</h1>"+
                "";
        emailService.sendEmailWithHtml("istakarhussain1992@gmail.com",
                "Email from my EmailSender App",html);
    }

    @Test
    void sendEmailWithFile(){
        System.out.println("Sending email");
        emailService.sendEmailWithFile("istakarhussain1992@gmail.com",
                "Email from my EmailSender App","This is a test mail for sending file",new File("C:\\Users\\INDIA\\Desktop\\Email-Sender-App\\Email sender App\\Email-sender-App\\src\\main\\resources\\static\\images\\farheen photo.png"));
    }

    @Test
    void sendEmailWithFileWithStrim(){
        System.out.println("Sending email");
        File file = new File("C:\\Users\\INDIA\\Desktop\\Documenta farheen\\Farheen-cell.github.io\\images\\netlify.png");
        try {
            InputStream is = new FileInputStream(file);
            emailService.sendEmailWithFile("istakarhussain1992@gmail.com",
                    "Email from my EmailSender App","This is a test mail for sending file",is);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        emailService.sendEmailWithFile("istakarhussain1992@gmail.com",
                "Email from my EmailSender App","This is a test mail for sending file",new File("C:\\Users\\INDIA\\Desktop\\Email-Sender-App\\Email sender App\\Email-sender-App\\src\\main\\resources\\static\\images\\farheen photo.png"));
    }
}
