package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Bean 
    public void mailSender() {
        System.out.println("hahahahas===================================");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("chenmenghui1991@163.com");
        message.setTo("119889525@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("简单邮件内容");
        mailSender.send(message);
    }
}
