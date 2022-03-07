package by.declarant.client.service;

import by.declarant.client.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonServiceTest {

    private static final String URI = "http://localhost:8082";

    private WebTestClient webClient;

    @Autowired
    private PersonService personService;

    @Test
    public void getPerson() throws JsonProcessingException {
        assertNotNull(personService);
        Person expectedPerson = Person.builder().id(1L).name("Ivan").build();
        Person person = personService.getPersonById(1L);
        assertEquals(expectedPerson, person);
    }

    @Test
    public void getPersonById() {
        Person expectedPerson = Person.builder().id(1L).name("Ivan").build();
        long id = expectedPerson.getId();
        this.webClient.get().uri(URI + "/person/" + id).exchange().expectStatus().isOk()
                .expectBody(Person.class).isEqualTo(expectedPerson);
    }

    @Test
    public void getPersonByName() {
        Person expectedPerson = Person.builder().id(1L).name("Ivan").build();
        String name = expectedPerson.getName();
        this.webClient.get().uri(URI + "/person/" + name).exchange().expectStatus().isOk()
                .expectBody(Person.class).isEqualTo(expectedPerson);
    }

    @Test
    public void savePerson() {
        Person expectedPerson = Person.builder().id(1L).name("Ivan").build();
        this.webClient.post()
                .uri(URI + "/person/")
                .body(expectedPerson, Person.class)
                .exchange().expectStatus().isOk()
                .expectBody(Person.class).isEqualTo(expectedPerson);
    }

    @Test
    public void updatePerson() {
        Person expectedPerson = Person.builder().id(1L).name("Alexandr").build();
        this.webClient.put()
                .uri(URI + "/person/")
                .body(expectedPerson, Person.class)
                .exchange().expectStatus().isOk()
                .expectBody(Person.class).isEqualTo(expectedPerson);
    }

    @Test
    public void deletePerson() {
        Person expectedPerson = Person.builder().id(1L).name("Alexandr").build();
        long id = expectedPerson.getId();
        this.webClient.delete()
                .uri(URI + "/person/" + id)
                .exchange().expectStatus().isOk()
                .expectBody(Person.class).isEqualTo(expectedPerson);
    }

    @Test
    public void getAllPerson() {
        List<Person> list = Arrays.asList(
                Person.builder().id(1L).name("Ivan").build(),
                Person.builder().id(2L).name("Alexandr").build()
        );
        this.webClient.get()
                .uri(URI + "/person/")
                .exchange().expectStatus().isOk()
                .expectBodyList(Person.class).contains((Person) list);
    }

}