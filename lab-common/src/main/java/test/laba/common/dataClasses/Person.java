package test.laba.common.dataClasses;


import test.laba.common.exception.CreateError;
import test.laba.common.exception.VariableException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 * data class
 */
@XmlRootElement(name = "owner")
public class Person implements Comparable<Person>, Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.ZonedDateTime birthday; //Поле не может быть null
    private Integer height; //Значение поля должно быть больше 0
    private Location location; //Поле не может быть null

    public  Person() {
    }

    /**
     * the constructor create object person
     * @param name person name, not null
     * @param birthday person birthday, not null
     * @param height person height, not null and more than zero
     * @param location person location, not null
     * @throws CreateError throws if fields do not match the criteria
     */
    public Person(String name, ZonedDateTime birthday, Integer height, Location location) throws CreateError {
        if (name == null || name.isEmpty() || height <= 0  || location == null) {
            throw new CreateError(" ошибка при создании объекта Person: проверьте данные"
                   + " \nПоле name не может быть null или быть пустым"
                    + "\nПоле birthday не может быть null"
                    + "\nЗначение поля height должно быть больше 0"
                    + "\nПоле Location не может быть null");
        } else {
            this.name = name;
            this.birthday = birthday;
            this.height = height;
            this.location = location;
        }
    }

    public String getName() {
        return name;
    }
    public String getBirthdayString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return birthday.format(formatter);
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public Integer getHeight() {
        return height;
    }
    public Location getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }
    @XmlElement
    public void setBirthday(String birthday) throws VariableException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate parsed = LocalDate.parse(birthday, formatter);
            this.birthday = ZonedDateTime.of(parsed, LocalTime.MIDNIGHT, ZoneId.of("Europe/Berlin"));
        } catch (DateTimeException e) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                LocalDateTime parsed = LocalDateTime.parse(birthday, formatter);
                this.birthday = ZonedDateTime.of(parsed, ZoneId.of("Europe/Berlin"));
            } catch (DateTimeException exception) {
                throw new VariableException("Неправильный формат данных, повторите ввод в формате ДД-MM-ГГГГ или ДД-ММ-ГГГГ ЧЧ:ММ:СС, вы ввели: " + birthday);
            }
        }
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isRightPerson() {

        return name != null && !"".equals(name) && birthday != null && height > 0 && location !=  null && location.isRightLocation();
    }

    @Override
    public String toString() {
        if (name != null) {
            return "Person: "
                    + "name=" + name
                    + ", birthday=" + getBirthdayString()
                    + ", height=" + height
                    + ", location=" + location;
        } else {
            return "Person: " + "null";
        }
    }

    @Override
    public int compareTo(Person o) {
        return Comparator.comparing(Person :: getName).
                thenComparing(Person ::getBirthdayString).
                thenComparing(Person :: getHeight).
                thenComparing(Person :: getLocation).compare(this, o);
    }
}
