package ru.testfield.messagescheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.testfield.messagescheduler.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT msg FROM #{#entityName} AS msg WHERE msg.status LIKE :status")
    List<Message> findByStatus(@Param("status") Message.Status status);
}