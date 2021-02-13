package ru.testfield.messagescheduler.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude="messages",callSuper = true)
public class Client extends Person{
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, orphanRemoval = true)
    private List<Message> messages;
}