package by.declarant.client.service.impl;

import by.declarant.client.model.Contacts;
import by.declarant.client.service.ContactsService;
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

import java.util.Arrays;
import java.util.List;

/**
 * Класс сервис для объекта контакт, со свойствами <b>restTemplate</b>, <b>httpHeaders</b>, <b>objectMapper</b>.
 * @autor Alexandr Zanko
 * @version 1.1
 */
@Data
@NoArgsConstructor
//@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ContactsServiceImpl implements ContactsService {

    /** Константа - URL-адресс REST модуля */
    private static final String HTTP_URL_CONTACTS = "http://localhost:8082/contacts/";
    /** Клиент для выполнения HTTP-запросов */
    private RestTemplate restTemplate;
    /** Заголовки HTTP-запроса */
    private HttpHeaders httpHeaders;
    /** Объкт для конвертации в строку JSON */
    private ObjectMapper objectMapper;

    @Autowired
    public ContactsServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Метод - возвращает объект Contacts
     * @param id - уникальный идентификатор
     * @return - объект типа Contacts
     */
    @Override
    public Contacts getContactById(Long id) throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(id), httpHeaders);
        ResponseEntity<Contacts> contactsResponseEntity = restTemplate.getForEntity(HTTP_URL_CONTACTS, Contacts.class, request);
        return contactsResponseEntity.getBody();
    }

    /**
     * Метод - сохраняет объект Contacts
     * @param contacts - новый объект
     * @return - объект типа Contacts
     */
    @Override
    public Contacts saveContacts(Contacts contacts) throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(contacts), httpHeaders);
        ResponseEntity<Contacts> contactsResponseEntity = restTemplate.postForEntity(HTTP_URL_CONTACTS, request, Contacts.class);
        return contactsResponseEntity.getBody();
    }

    /**
     * Метод - обновляет объект Contacts
     * @param contacts - обновленный объект
     */
    @Override
    public void updateContacts(Contacts contacts) throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(contacts), httpHeaders);
        restTemplate.put(HTTP_URL_CONTACTS, request, Contacts.class);
    }

    /**
     * Метод - удаление объект Contacts
     * @param id - уникальный идентификатор
     */
    @Override
    public void deleteContacts(Long id) throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(id), httpHeaders);
        restTemplate.delete(HTTP_URL_CONTACTS, request, Contacts.class);
    }

    /**
     * Метод - сохраняет объект Contacts
     * @return - Колеекцию объектов типа Contacts
     */
    @Override
    public List<Contacts> getAllContact() {
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<Contacts[]> contactsResponseEntity = restTemplate.getForEntity(HTTP_URL_CONTACTS, Contacts[].class, request);
        return Arrays.asList(contactsResponseEntity.getBody());
    }

}
