package ru.testfield.messagescheduler.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.testfield.messagescheduler.MessageSchedulerApplication;
import ru.testfield.messagescheduler.model.*;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = MessageSchedulerApplication.class)
public class MessageRepositoryTest {

    final TestDataProvider testDataProvider = new TestDataProvider();

    @Autowired
    private AddresseeRepository addresseeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void repositoryTest(){
        deleteAll();
        saveAddressee(testDataProvider.supplyAddressee());
        assertEquals(1, addresseeRepository.count());
        saveClient(testDataProvider.supplyClient());
        assertEquals(1, clientRepository.count());

        saveTest();
        countTest();
        findByStatusTest();

        deleteAll();
    }

    private void countTest() {
        assertEquals(5, messageRepository.count());
    }

    private void saveTest() {
        final Addressee addressee = addresseeRepository.findAll().get(0);
        final Client client = clientRepository.findAll().get(0);
        createMessage(addressee,client,"text to send1", Message.MessageStatus.SCHEDULED, Set.of(MediaType.EMAIL));
        createMessage(addressee,client,"text to send2", Message.MessageStatus.SENT, Set.of(MediaType.TELEGRAM));
        createMessage(addressee,client,"text to send3", Message.MessageStatus.CANCELED, Set.of(MediaType.EMAIL));
        createMessage(addressee,client,"text to send4", Message.MessageStatus.SCHEDULED, Set.of(MediaType.SMS));
        createMessage(addressee,client,"text to send5", Message.MessageStatus.SENT, Set.of(MediaType.TELEGRAM));
    }

    private void findByStatusTest() {
        assertEquals(2, messageRepository.findByMessageStatus(Message.MessageStatus.SCHEDULED).size());
        assertEquals(2, messageRepository.findByMessageStatus(Message.MessageStatus.SENT).size());
        assertEquals(1, messageRepository.findByMessageStatus(Message.MessageStatus.CANCELED).size());
    }

    private void createMessage(Addressee addressee, Client client, String textToSend, Message.MessageStatus messageStatus, Set<MediaType> mediaTypes) {
        final Message message = new Message(textToSend, client, addressee, messageStatus, mediaTypes, ZonedDateTime.now(),
                0, Set.of(DayOfWeek.MONDAY,DayOfWeek.WEDNESDAY));
        messageRepository.save(message);
    }

    private void saveClient(Client client) {
        clientRepository.save(client);
        assertNotNull(client.getId());
    }

    private void saveAddressee(Addressee addressee) {
        addresseeRepository.save(addressee);
        assertNotNull(addressee.getId());
    }

    private void deleteAll() {
        messageRepository.deleteAll();
        addresseeRepository.deleteAll();
        clientRepository.deleteAll();
        assertEquals(0, addresseeRepository.count());
        assertEquals(0, clientRepository.count());
        assertEquals(0, messageRepository.count());
    }

}
