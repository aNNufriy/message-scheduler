package ru.testfield.messagescheduler.service;

import org.springframework.stereotype.Service;
import ru.testfield.messagescheduler.model.Message;
import ru.testfield.messagescheduler.model.ScheduledMessageDecorator;
import ru.testfield.messagescheduler.repository.MessageRepository;

import java.util.HashMap;
import java.util.List;

@Service
public class MessageScheduler {

    private final MessageRepository messageRepository;

    private final HashMap<Long, ScheduledMessageDecorator> scheduledMessages = new HashMap<>();

    public MessageScheduler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void scheduleStoredMessages() {
        final List<Message> storedMessages = messageRepository.findByStatus(Message.Status.SCHEDULED);
        for (Message message : storedMessages) {
            if (message.getStatus() != Message.Status.SCHEDULED) {
                throw new IllegalStateException();
            }
            scheduleMessage(message);
        }
    }

    public void scheduleMessage(Message message){
        var scheduledMessage = new ScheduledMessageDecorator(message, message.getScheduledTime(), null);
        scheduledMessages.putIfAbsent(message.getId(), scheduledMessage);
    }
}