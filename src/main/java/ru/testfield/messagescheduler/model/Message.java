package ru.testfield.messagescheduler.model;

import lombok.Data;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
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

    private ZonedDateTime time;

    private int repeatInterval;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass=DayOfWeek.class)
    private Set<DayOfWeek> weekdays;

    public Message() {
    }

    public Message(String text, Client client, Addressee addressee, MessageStatus messageStatus,
                   Set<MediaType> mediaTypes, ZonedDateTime time, int repeatInterval, Set<DayOfWeek> weekdays) {
        this.text = text;
        this.client = client;
        this.addressee = addressee;
        this.messageStatus = messageStatus;
        this.mediaTypes = mediaTypes;
        this.time = time;
        this.repeatInterval = repeatInterval;
        this.weekdays = weekdays;
    }

    public enum MessageStatus {
        SCHEDULED,SENT,CANCELED
    }
}