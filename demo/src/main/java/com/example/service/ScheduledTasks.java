package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private EmailService emailService;
    @Autowired
    private CrawlerService crawlerService;
    @Scheduled(cron = "0 54/30 * * * ?")
    public void checkWebSite() {
        if (crawlerService.hasGood()) {
            emailService.mailSender();
        } else {
            System.out.println("还没有东西------------------------------------------");
        }
        log.info("The time is now{}", dateFormat.format(new Date()));
    }
}
