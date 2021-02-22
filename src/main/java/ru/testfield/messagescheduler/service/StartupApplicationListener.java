package ru.testfield.messagescheduler.service;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final MessageScheduler messageScheduler;

    public StartupApplicationListener(MessageScheduler messageScheduler) {
        this.messageScheduler = messageScheduler;
    }

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {
        messageScheduler.scheduleStoredMessages();
    }
}