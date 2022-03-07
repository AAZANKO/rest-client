package by.declarant.client.service.impl;

import by.declarant.client.model.Person;
import by.declarant.client.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс сервис для объекта персона, со свойствами со свойствами <b>restTemplate</b>, <b>httpHeaders</b>, <b>objectMapper</b>.
 * @autor Alexandr Zanko
 * @version 1.1
 */
@Data
@NoArgsConstructor
//@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PersonServiceImpl implements PersonService {

    /** Константа - URL-адресс REST модуля */
    private static final String HTTP_URL_PERSON = "http://localhost:8082/person/";

    /** Клиент для выполнения HTTP-запросов */
    private RestTemplate restTemplate;
    /** Заголовки HTTP-запроса */
    private HttpHeaders httpHeaders;
    /** Объкт для конвертации в строку JSON */
    private ObjectMapper objectMapper;

    @Autowired
    public PersonServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Метод - возвращает объект Person
     * @param id - уникальный идентификатор
     * @return - объект типа Person
     */
    @Override
    public Person getPersonById(Long id) throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(id), httpHeaders);
        ResponseEntity<Person> personResponseEntity = restTemplate.getForEntity(HTTP_URL_PERSON, Person.class, request);
        return personResponseEntity.getBody();
    }

    /**
     * Метод - возвращает объект Person
     * @param name - имя объекта
     * @return - объект типа Person
     */
    @Override
    public Person getPersonByName(String name) throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(name), httpHeaders);
        ResponseEntity<Person> personResponseEntity = restTemplate.getForEntity(HTTP_URL_PERSON, Person.class, request);
        return personResponseEntity.getBody();
    }

    /**
     * Метод - сохраняет объект Person
     * @param person - новый объект типа Person
     * @return - объект типа Person
     */
    @Override
    public Person savePerson(Person person) throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(person), httpHeaders);
        ResponseEntity<Person> personResponseEntity = restTemplate.postForEntity(HTTP_URL_PERSON, request, Person.class);
        return personResponseEntity.getBody();
    }

    /**
     * Метод - сохраняет объект Person
     * @param person - новый объект типа Person
     */
    @Override
    public void updatePerson(Person person) throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(person), httpHeaders);
        restTemplate.put(HTTP_URL_PERSON, request, Person.class);
    }

    /**
     * Метод - удаляет объект Person
     * @param id - уникальный идентификатор
     */
    @Override
    public void deletePerson(Long id) throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(id), httpHeaders);
        restTemplate.delete(HTTP_URL_PERSON, request, Person.class);
    }

    /**
     * Метод - возвращает коллекцию объектов Person
     * @return - коллекция объектов типа Person
     */
    @Override
    public List<Person> getAllPerson() {
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<Person[]> contactsResponseEntity = restTemplate.getForEntity(HTTP_URL_PERSON, Person[].class, request);
        Person[] persons = contactsResponseEntity.getBody();
        return Arrays.asList(persons);
    }

}
