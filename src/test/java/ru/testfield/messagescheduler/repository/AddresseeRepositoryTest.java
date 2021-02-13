package ru.testfield.messagescheduler.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.testfield.messagescheduler.MessageSchedulerApplication;
import ru.testfield.messagescheduler.model.Addressee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = MessageSchedulerApplication.class)
public class AddresseeRepositoryTest {

    final TestDataProvider testDataProvider = new TestDataProvider();

    @Autowired
    private AddresseeRepository clientRepository;

    @Test
    public void repositoryTest(){
        deleteAll();
        final int n = 100;
        for(int i = 0; i<n; i++) {
            createAddressee(testDataProvider.supplyAddressee());
        }
        assertEquals(n,clientRepository.count());

        deleteAll();
    }

    private void createAddressee(Addressee addressee) {
        clientRepository.save(addressee);
        assertNotNull(addressee.getId());
    }

    private void deleteAll() {
        clientRepository.deleteAll();
        assertEquals(0, clientRepository.count());
    }

}
