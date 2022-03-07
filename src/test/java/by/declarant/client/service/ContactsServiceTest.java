package by.declarant.client.service;

import by.declarant.client.model.Contacts;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactsServiceTest {

    private static final String URI = "http://localhost:8082";

    private WebTestClient webClient;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContactsService contactsService;

    @Test
    public void getContact() throws JsonProcessingException {
        assertNotNull(contactsService);
        Contacts expectedContact = Contacts.builder().id(1L).telephone("+375-29-2274670").idPerson(1L).typePhone(1L).build();
        Contacts contact = contactsService.getContactById(1L);
        assertEquals(expectedContact, contact);
    }

    @Test
    public void getRestContact() {
        Contacts expectedContact = Contacts.builder().id(1L).telephone("+375-29-2274670").idPerson(1L).typePhone(1L).build();
        long id = expectedContact.getId();
        this.webClient.get().uri(URI + "/contacts/"+ id).exchange().expectStatus().isOk()
                .expectBody(Contacts.class).isEqualTo(expectedContact);
    }

    @Test
    public void saveContacts() {
        Contacts expectedContact = Contacts.builder().id(2L).telephone("+375-29-9399959").idPerson(2L).typePhone(2L).build();
        this.webClient.post()
                .uri(URI + "/contacts/")
                .body(expectedContact, Contacts.class)
                .exchange().expectStatus().isOk()
                .expectBody(Contacts.class).isEqualTo(expectedContact);
    }

    @Test
    public void updateContacts() {
        Contacts expectedContact = Contacts.builder().id(2L).telephone("+375-29-2274670").idPerson(2L).typePhone(2L).build();
        this.webClient.put()
                .uri(URI + "/contacts/")
                .body(expectedContact, Contacts.class)
                .exchange().expectStatus().isOk()
                .expectBody(Contacts.class).isEqualTo(expectedContact);
    }

    @Test
    public void deleteContacts() {
        Contacts expectedContact = Contacts.builder().id(2L).telephone("+375-29-2274670").idPerson(2L).typePhone(2L).build();
        long id = expectedContact.getId();
        this.webClient.delete()
                .uri(URI + "/contacts/" + id)
                .exchange().expectStatus().isOk()
                .expectBody(Contacts.class).isEqualTo(expectedContact);
    }

    @Test
    public void getAllContact() {
        List<Contacts> list = Arrays.asList(
                Contacts.builder().id(1L).telephone("+375-29-2274670").idPerson(1L).typePhone(1L).build(),
                Contacts.builder().id(2L).telephone("+375-29-2274670").idPerson(2L).typePhone(2L).build()
        );
        this.webClient.get()
                .uri(URI + "/contacts/")
                .exchange().expectStatus().isOk()
                .expectBodyList(Contacts.class).contains((Contacts) list);
    }
}