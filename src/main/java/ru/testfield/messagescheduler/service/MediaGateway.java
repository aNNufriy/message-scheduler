package ru.testfield.messagescheduler.service;

import ru.testfield.messagescheduler.model.Message;

import java.util.concurrent.Future;

public interface MediaGateway {
    Future<Message> send(Message message);
}
