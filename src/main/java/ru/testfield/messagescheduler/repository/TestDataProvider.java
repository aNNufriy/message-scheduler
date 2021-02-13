package ru.testfield.messagescheduler.repository;

import ru.testfield.messagescheduler.model.Addressee;
import ru.testfield.messagescheduler.model.Client;
import ru.testfield.messagescheduler.model.Contact;
import ru.testfield.messagescheduler.model.MediaType;

import java.util.Set;
import java.util.UUID;

public class TestDataProvider {
    public Client supplyClient() {
        Client client = new Client();

        client.setFirstName("John"+ UUID.randomUUID().toString());
        client.setMiddleName("Jr."+UUID.randomUUID().toString());
        client.setLastName("Brown"+UUID.randomUUID().toString());

        return client;
    }

    public Addressee supplyAddressee() {
        final Addressee addressee = new Addressee();

        Contact contact1 = new Contact(MediaType.EMAIL, UUID.randomUUID()+"@brown.com");
        Contact contact2 = new Contact(MediaType.SMS,"+"+UUID.randomUUID());
        Contact contact3 = new Contact(MediaType.TELEGRAM,"+"+UUID.randomUUID());
        addressee.setContacts(Set.of(contact1,contact2,contact3));

        addressee.setFirstName("John"+UUID.randomUUID().toString());
        addressee.setMiddleName("Jr."+UUID.randomUUID().toString());
        addressee.setLastName("Brown"+UUID.randomUUID().toString());

        return addressee;
    }
}
