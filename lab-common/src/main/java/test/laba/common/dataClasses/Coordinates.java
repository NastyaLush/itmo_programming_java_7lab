package test.laba.common.dataClasses;

import test.laba.common.exception.VariableException;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Comparator;

/**
 * data class
 */
@XmlRootElement(name = "coordinates")
@XmlType(propOrder = { "x", "y" })
public class Coordinates implements Comparable<Coordinates>, Serializable {
    private Integer x; //Значение поля должно быть больше -233, Поле не может быть null
    private Float y; //Поле не может быть null

    private final int minNumberOfX = -233;

    public Coordinates() {
    }
    /**
     * the constructor create location object
     */
    public Coordinates(Integer x, Float y)  {
        if (x >= minNumberOfX) {
            this.x = x;
        }
        this.y = y;

    }

    public Integer getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public void setX(Integer x) throws VariableException {
        if (x >= minNumberOfX) {
            this.x = x;
        } else {
            throw new VariableException("Ошибка при добавлении координаты X:значение поля x должно быть больше -233, Поле не может быть null\n"
                    + "Вы ввели: " + x);
        }
    }

    public void setY(Float y) throws VariableException {
        if (y != null) {
            this.y = y;
        } else {
            throw  new VariableException("Ошибка при добавлении координаты Y: Поле не может быть null\n"
                    + "Вы ввели: " + y);
        }

    }

    @Override
    public String toString() {
        return "Coordinates{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }

    @Override
    public int compareTo(Coordinates o) {
        return Comparator.comparing(Coordinates :: getX).
        thenComparing(Coordinates :: getY).compare(this, o);
    }
}
