package com.email.Email.sender.App.controller.api;

import com.email.Email.sender.App.paylod.CustomeResponse;
import com.email.Email.sender.App.paylod.EmailRequest;
import com.email.Email.sender.App.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    EmailService emailService;



    @PostMapping("/send")
    public ResponseEntity<CustomeResponse> sendEmail(@RequestBody EmailRequest request){
        emailService.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());
           return new ResponseEntity<CustomeResponse>(
                   new CustomeResponse("Email send Successfully !!",HttpStatus.OK,true),
                   HttpStatus.OK
           );
    }

    @PostMapping("/send-with-file")
    public ResponseEntity<CustomeResponse> sendWithFile(@RequestPart EmailRequest request,
                                                        @RequestPart MultipartFile file) throws IOException {
        emailService.sendEmailWithFile(request.getTo(), request.getSubject(),
                request.getMessage(), file.getInputStream());
        return new ResponseEntity<CustomeResponse>(
                new CustomeResponse("Email send Successfully !!", HttpStatus.OK, true),
                HttpStatus.OK
        );
    }
}
