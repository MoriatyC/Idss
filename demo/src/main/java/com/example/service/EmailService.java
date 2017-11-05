package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Bean 
    public void mailSender() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("chenmenghui1991@163.com");
        message.setTo("119889525@qq.com");
        message.setSubject("主题：搬瓦工降价通知");
        message.setText("你想买的搬瓦工产品终于有货了");
        mailSender.send(message);
        System.out.println("已发送邮件==============================================");
    }
}
