package ru.testfield.messagescheduler.model;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    private ZonedDateTime scheduledTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Addressee addressee;

    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;

    public Message() {
    }

    public Message(String text, Client client, Addressee addressee, Status status) {
        this.text = text;
        this.client = client;
        this.addressee = addressee;
        this.status = status;
    }

    public enum Status {
        SCHEDULED,SENT,CANCELED;
    }
}
