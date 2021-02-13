package ru.testfield.messagescheduler.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.testfield.messagescheduler.MessageSchedulerApplication;
import ru.testfield.messagescheduler.model.Client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = MessageSchedulerApplication.class)
public class ClientRepositoryTest {

    final TestDataProvider testDataProvider = new TestDataProvider();

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void repositoryTest(){
        deleteAll();
        final int n = 100;
        for(int i=0; i<n; i++) {
            saveClient(testDataProvider.supplyClient());
        }
        assertEquals(n,clientRepository.count());
        deleteAll();
        assertEquals(0,clientRepository.count());
    }

    private void saveClient(Client client) {
        clientRepository.save(client);
        assertNotNull(client.getId());
    }

    private void deleteAll() {
        clientRepository.deleteAll();
        assertEquals(0, clientRepository.findAll().size());
    }

}
