package by.declarant.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс персона, со свойствами <b>id</b> и <b>name</b>.
 * @autor Alexandr Zanko
 * @version 1.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    /** Поле уникальный идентификатор */
    private long id;
    /** Поле наименование */
    private String name;
}
