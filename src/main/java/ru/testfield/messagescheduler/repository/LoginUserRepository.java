package ru.testfield.messagescheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testfield.messagescheduler.model.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
}