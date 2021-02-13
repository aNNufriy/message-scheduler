package ru.testfield.messagescheduler.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Contact() {
    }

    public Contact(MediaType mediaType, String identifier) {
        this.mediaType = mediaType;
        this.identifier = identifier;
    }

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    private String identifier;

    @ManyToOne(fetch = FetchType.LAZY)
    private Addressee addressee;
}
