package by.declarant.client.controller;

import by.declarant.client.model.Contacts;
import by.declarant.client.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Класс контроллер для объекта контакт, со свойствами <b>contactsService</b>.
 *
 * @version 1.1
 * @autor Alexandr Zanko
 */
@Controller
public class ContactsController {

    /**
     * доступ к методам слоя Service
     */
    private ContactsService contactsService;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param contactsService - получение методов из слоя Service
     */
    @Autowired
    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    /**
     * Метод - возвращает объект Contacts
     *
     * @param id    - уникальный идентификатор
     * @param model - временное хранение данных
     * @return - маппинг на страничку
     */
    @GetMapping("/contact/{id}")
    public String getById(@PathVariable("id") long id, Model model) {
        try {
            model.addAttribute("contact", contactsService.getContactById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "contact";
    }

    /**
     * Метод - возвращает коллекцию объектов Contacts
     *
     * @param model - временное хранение данных
     * @return - маппинг на страничку
     */
    @GetMapping("/contact/")
    public String getAll(Model model) {
        try {
            model.addAttribute("contacts", contactsService.getAllContact());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "contact";
    }

    /**
     * Метод - добавляет новый объект Contacts
     *
     * @param contact - новый объект
     * @param model   - временное хранение данных
     * @return - маппинг на страничку
     */
    @PostMapping("/contact/create")
    public String create(@ModelAttribute("contact") Contacts contact, Model model) {
        try {
            model.addAttribute("contact", contactsService.saveContacts(contact));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "contact";
    }

    /**
     * Метод - обновляет объект Contacts
     *
     * @param contact - измененный объект
     * @param model   - временное хранение данных
     * @return - маппинг на страничку
     */
    @PostMapping("/contact/update")
    public String update(@ModelAttribute("contact") Contacts contact, Model model) {
        try {
            contactsService.updateContacts(contact);
            model.addAttribute("contact", contact);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "contact";
    }

    /**
     * Метод - удаляет объект Contacts
     *
     * @param contact - объект для удаления
     * @param model   - временное хранение данных
     * @return - маппинг на страничку
     */
    @PostMapping("/contact/delete")
    public String delete(@ModelAttribute("contact") Contacts contact, Model model) {
        try {
            contactsService.deleteContacts(contact.getId());
            model.addAttribute("contact", contact);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "contact";
    }
}
