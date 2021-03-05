package ru.testfield.messagescheduler.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
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
    private MessageStatus messageStatus = MessageStatus.SCHEDULED;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass=MediaType.class)
    private Set<MediaType> mediaTypes;

    public Message() {
    }

    public Message(String text, Client client, Addressee addressee, MessageStatus messageStatus, Set<MediaType> mediaTypes) {
        this.text = text;
        this.client = client;
        this.addressee = addressee;
        this.messageStatus = messageStatus;
        this.mediaTypes = mediaTypes;
    }

    public enum MessageStatus {
        SCHEDULED,SENT,CANCELED
    }
}