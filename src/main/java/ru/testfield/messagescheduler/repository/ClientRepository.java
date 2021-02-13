package ru.testfield.messagescheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testfield.messagescheduler.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
