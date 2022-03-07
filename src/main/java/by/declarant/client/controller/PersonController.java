package by.declarant.client.controller;

import by.declarant.client.model.Person;
import by.declarant.client.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Класс контроллер для объекта персона, со свойствами <b>personService</b>.
 *
 * @version 1.1
 * @autor Alexandr Zanko
 */
@Controller
public class PersonController {

    /**
     * доступ к методам слоя Service
     */
    private PersonService personService;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param personService - получение методов из слоя Service
     */
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Метод - возвращает объект Person
     *
     * @param id    - уникальный идентификатор
     * @param model - временное хранение данных
     * @return - маппинг на страничку
     */
    @GetMapping("/person/{id}")
    public String getById(@PathVariable("id") long id, Model model) {
        try {
            model.addAttribute("person", personService.getPersonById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "person";
    }

    /**
     * Метод - возвращает объект Person
     *
     * @param name  - наименование объекта
     * @param model - временное хранение данных
     * @return - маппинг на страничку
     */
    @GetMapping("/person/{name}")
    public String getByName(@PathVariable("name") String name, Model model) {
        try {
            model.addAttribute("person", personService.getPersonByName(name));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "person";
    }

    /**
     * Метод - возвращает коллекцию объектав Person
     *
     * @param model - временное хранение данных
     * @return - маппинг на страничку
     */
    @GetMapping("/person/")
    public String getAll(Model model) {
        try {
            model.addAttribute("persons", personService.getAllPerson());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "person";
    }

    /**
     * Метод - добавляет новый объект Person
     *
     * @param person - новый объект
     * @param model  - временное хранение данных
     * @return - маппинг на страничку
     */
    @PostMapping("/person/create")
    public String create(@ModelAttribute("person") Person person, Model model) {
        try {
            model.addAttribute("person", personService.savePerson(person));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "person";
    }

    /**
     * Метод - обновляет объект Person
     *
     * @param person - измененный объект
     * @param model  - временное хранение данных
     * @return - маппинг на страничку
     */
    @PostMapping("/person/update")
    public String update(@ModelAttribute("person") Person person, Model model) {
        try {
            personService.updatePerson(person);
            model.addAttribute("person", person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "person";
    }

    /**
     * Метод - удаляет объект Person
     *
     * @param person - объект удаления
     * @param model  - временное хранение данных
     * @return - маппинг на страничку
     */
    @PostMapping("/person/delete")
    public String delete(@ModelAttribute("person") Person person, Model model) {
        try {
            personService.deletePerson(person.getId());
            model.addAttribute("person", person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "person";
    }
}
