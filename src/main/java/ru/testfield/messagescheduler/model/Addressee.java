package ru.testfield.messagescheduler.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude={"contacts","messages"},callSuper = true)
public class Addressee extends Person {
    @OneToMany(mappedBy = "addressee", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Contact> contacts;

    @OneToMany(mappedBy = "addressee", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Message> messages;

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
        for(Contact contact: this.contacts){
            contact.setAddressee(this);
        }
    }

    public void setMessages(Set<Contact> contacts) {
        this.contacts = contacts;
        for(Contact contact: this.contacts){
            contact.setAddressee(this);
        }
    }
}