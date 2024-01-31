package com.sparrow.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication(scanBasePackages = "com.sparrow.*")
//@MapperScan(basePackages = {"com."})
@Slf4j
public class ApplicationBoot {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApplicationBoot.class);
        springApplication.addListeners(new ApplicationListener<ApplicationStartingEvent>() {
            @Override
            public void onApplicationEvent(ApplicationStartingEvent event) {
                log.info("prepare before bean init");
            }
        });
        springApplication.addListeners(new ApplicationListener<ContextRefreshedEvent>() {
            @Override
            public void onApplicationEvent(ContextRefreshedEvent event) {
                log.info("application startup at {}", event.getTimestamp());
            }
        });
        springApplication.addListeners(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                log.info("application closed at at {}", event.getTimestamp());
            }
        });
        springApplication.run(args);
    }
}