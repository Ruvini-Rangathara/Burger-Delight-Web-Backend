package com.burger.delight.burgerdelightbackend2.controller;

import com.burger.delight.burgerdelightbackend2.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

/**
 * Author: ruu
 * Created: 2024-01-25 21.23
 */


@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping(value = "/api/v1/mail")
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/send")
    public String sendMail(@RequestBody MailDTO mailDTO){
        System.out.println("MailController.sendMail  : "+mailDTO.toString());
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(mailDTO.getTo());
            simpleMailMessage.setSubject(mailDTO.getSubject());
            simpleMailMessage.setText(mailDTO.getBody());
            mailSender.send(simpleMailMessage);

            System.out.println("MailController.sendMail: mail sent");

            return "00";
        }catch (Exception e){
            System.out.println("MailController.sendMail: exception :"+e.getMessage());
            return "01";
        }
    }

}
