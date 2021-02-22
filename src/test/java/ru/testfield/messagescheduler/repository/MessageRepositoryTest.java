package ru.testfield.messagescheduler.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.testfield.messagescheduler.MessageSchedulerApplication;
import ru.testfield.messagescheduler.model.*;

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
        createMessage(addressee,client,"text to send1", Message.Status.SCHEDULED);
        createMessage(addressee,client,"text to send2", Message.Status.SENT);
        createMessage(addressee,client,"text to send3", Message.Status.CANCELED);
        createMessage(addressee,client,"text to send4", Message.Status.SCHEDULED);
        createMessage(addressee,client,"text to send5", Message.Status.SENT);
    }

    private void findByStatusTest() {
        assertEquals(2, messageRepository.findByStatus(Message.Status.SCHEDULED).size());
        assertEquals(2, messageRepository.findByStatus(Message.Status.SENT).size());
        assertEquals(1, messageRepository.findByStatus(Message.Status.CANCELED).size());
    }

    private void createMessage(Addressee addressee, Client client, String textToSend, Message.Status status) {
        final Message message = new Message(textToSend, client, addressee, status);
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
