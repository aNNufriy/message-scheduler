package ru.testfield.messagescheduler.model;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;
}