package by.declarant.client.service;

import by.declarant.client.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * Интерфейс сервис для объекта персона.
 * @autor Alexandr Zanko
 * @version 1.1
 */
public interface PersonService {

    /**
     * Метод - возвращает объект Person
     * @param id - уникальный идентификатор
     * @return - объект типа Person
     */
    Person getPersonById(Long id) throws JsonProcessingException;

    /**
     * Метод - возвращает объект Person
     * @param name - имя объекта
     * @return - объект типа Person
     */
    Person getPersonByName(String name) throws JsonProcessingException;

    /**
     * Метод - сохраняет объект Person
     * @param person - новый объект типа Person
     * @return - объект типа Person
     */
    Person savePerson(Person person) throws JsonProcessingException;

    /**
     * Метод - сохраняет объект Person
     * @param person - новый объект типа Person
     */
    void updatePerson(Person person) throws JsonProcessingException;

    /**
     * Метод - удаляет объект Person
     * @param id - уникальный идентификатор
     */
    void deletePerson(Long id) throws JsonProcessingException;

    /**
     * Метод - возвращает коллекцию объектов Person
     * @return - коллекция объектов типа Person
     */
    List<Person> getAllPerson();

}
