package test.laba.client.console;

import test.laba.client.mainClasses.Location;
import test.laba.client.mainClasses.UnitOfMeasure;
import test.laba.client.mainClasses.Coordinates;
import test.laba.client.mainClasses.Person;
import test.laba.client.mainClasses.Product;

import java.util.HashMap;


public class SaveCollection {
    public String save(HashMap<Long, Product> productHashMap) {
        StringBuilder s = new StringBuilder("<root>" + '\n' + '\t');

        for (Product product : productHashMap.values()) {
            s.append(saveProduct(product));
        }
        return s.toString() + '\n' + "</root>";
    }
    private String saveLocation(Location location) {
        String s = "<location>\n\t\t\t\t";
        if (location.getX() != null) {
            s += "<x>" + location.getX() + "</x> " + '\n';
        }
        if (location.getY() != null) {
            s += "\t\t\t\t" + "<y>" + location.getY() + "</y>" + '\n';
        }
        s += "\t\t\t\t" + "<name>" + location.getName() + "</name>" + '\n' + "\t\t\t</location>";
        return s;
    }
    private String saveCoordinates(Coordinates coordinates) {
        return "<coordinates>\n"
                + "\t\t\t<x>" + coordinates.getX() + "</x>\n"
                + "\t\t\t<y>" + coordinates.getY() + "</y>\n"
                + "\t\t</coordinates>";
    }
    private String saveUnitOfMeasure(UnitOfMeasure unitOfMeasure) {

        return "<unitOfMeasure>" + unitOfMeasure + "</unitOfMeasure>";
    }
    private String savePerson(Person person) {
        String s;
        s = "<owner>" + "\n\t"
                + "\t\t" + "<name>" + person.getName() + "</name>" + '\n'
                + "\t\t\t" + "<birthday>" + person.getBirthday() + "</birthday>" + '\n';
        if (person.getHeight() != null) {
            s += "\t\t\t" + "<height>" + person.getHeight() + "</height>" + '\n';
        }
        s += "\t\t\t" + saveLocation(person.getLocation()) + '\n' + '\t' + '\t' + "</owner>";
        return s;
    }
    private String saveProduct(Product product) {
        String s = "<product>" + '\n'
                + "\t\t" + "<name>" + product.getName() + "</name>" + '\n'
                + "\t\t" + saveCoordinates(product.getCoordinates()) + '\n'
                + "\t\t" + "<price>" + product.getPrice() + "</price>" + '\n';
        if (product.getManufactureCost() != null) {
            s += "\t\t" + "<manufactureCost>" + product.getManufactureCost() + "</manufactureCost>" + '\n';
        }
        if (product.getUnitOfMeasure() != null) {
            s += "\t\t" + saveUnitOfMeasure(product.getUnitOfMeasure()) + '\n';
        }
        s += "\t\t" + savePerson(product.getOwner()) + '\n' + '\t'
                + "</product>";
        return s;

    }
}