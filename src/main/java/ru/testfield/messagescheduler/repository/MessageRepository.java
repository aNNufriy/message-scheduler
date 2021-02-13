package ru.testfield.messagescheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testfield.messagescheduler.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}