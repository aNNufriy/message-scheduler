package ru.testfield.messagescheduler.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.testfield.messagescheduler.model.LoginUser;
import ru.testfield.messagescheduler.repository.LoginUserRepository;

import java.util.List;

@RestController
public class LoginUserController {

    private LoginUserRepository loginUserRepository;

    public LoginUserController(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    public List<LoginUser> list(){
        return loginUserRepository.findAll();
    }
}