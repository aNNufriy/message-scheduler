package ru.testfield.messagescheduler.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class LoginUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String email;

    private String passBcryptHash;

    @Transient
    private String password;
}