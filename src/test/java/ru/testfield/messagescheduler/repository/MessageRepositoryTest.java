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

        final Addressee addressee = addresseeRepository.findAll().get(0);
        final Client client = clientRepository.findAll().get(0);

        createMessage(addressee,client,"text to send1");
        createMessage(addressee,client,"text to send2");
        createMessage(addressee,client,"text to send3");

        assertEquals(3, messageRepository.count());

        deleteAll();
    }

    private void createMessage(Addressee addressee, Client client, String textToSend) {
        final Message message = new Message(textToSend, client, addressee);
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
