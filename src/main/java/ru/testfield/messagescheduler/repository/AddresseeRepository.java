package ru.testfield.messagescheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testfield.messagescheduler.model.Addressee;

public interface AddresseeRepository extends JpaRepository<Addressee, Long> {
}