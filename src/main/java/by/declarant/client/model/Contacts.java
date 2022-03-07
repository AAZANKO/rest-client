package by.declarant.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс контакт, со свойствами <b>id</b>, <b>telephone</b>, <b>idPerson</b>, <b>typePhone</b>.
 * @autor Alexandr Zanko
 * @version 1.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contacts {

    /** Поле уникальный идентификатор */
    private long id;
    /** Поле номер телефона */
    private String telephone;
    /** Поле уникальный идентификатор, связь с объектом Person */
    private long idPerson;
    /** Поле тип телефона */
    private long typePhone;
}
