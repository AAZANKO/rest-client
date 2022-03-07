package by.declarant.client.service;

import by.declarant.client.model.Contacts;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * Интерфейс сервис для объекта контакт.
 * @autor Alexandr Zanko
 * @version 1.1
 */
public interface ContactsService {

    /**
     * Метод - возвращает объект Contacts
     * @param id - уникальный идентификатор
     * @return - объект типа Contacts
     */
    Contacts getContactById(Long id) throws JsonProcessingException;

    /**
     * Метод - сохраняет объект Contacts
     * @param contacts - новый объект
     * @return - объект типа Contacts
     */
    Contacts saveContacts(Contacts contacts) throws JsonProcessingException;

    /**
     * Метод - обновляет объект Contacts
     * @param contacts - обновленный объект
     */
    void updateContacts(Contacts contacts) throws JsonProcessingException;

    /**
     * Метод - удаление объект Contacts
     * @param id - уникальный идентификатор
     */
    void deleteContacts(Long id) throws JsonProcessingException;

    /**
     * Метод - сохраняет объект Contacts
     * @return - Колеекцию объектов типа Contacts
     */
    List<Contacts> getAllContact();

}
