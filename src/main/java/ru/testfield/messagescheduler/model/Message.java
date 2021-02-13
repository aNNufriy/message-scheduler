package ru.testfield.messagescheduler.model;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Addressee addressee;

    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;

    public Message() {
    }

    public Message(String text, Client client, Addressee addressee) {
        this.text = text;
        this.client = client;
        this.addressee = addressee;
    }

    public enum Status {
        SCHEDULED,SENT,CANCELED;
    }
}
