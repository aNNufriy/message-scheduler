package ru.testfield.messagescheduler.model;

import java.time.ZonedDateTime;

public final class ScheduledMessageDecorator {
    private final Message message;
    private final ZonedDateTime scheduledTime;
    private final ZonedDateTime lastSentTime;

    public ScheduledMessageDecorator(Message message, ZonedDateTime scheduledTime, ZonedDateTime lastSentTime) {
        this.message = message;
        this.scheduledTime = scheduledTime;
        this.lastSentTime = lastSentTime;
    }
}
