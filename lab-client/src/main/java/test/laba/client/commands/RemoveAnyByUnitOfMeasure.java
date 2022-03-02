package test.laba.client.commands;


import test.laba.client.console.Console;
import test.laba.client.console.ConsoleParsing;
import test.laba.client.exception.VariableException;
import test.laba.client.mainClasses.Product;
import test.laba.client.mainClasses.Root;
import test.laba.client.mainClasses.UnitOfMeasure;

import java.util.HashMap;

public class RemoveAnyByUnitOfMeasure extends AbstractCommand {
    public RemoveAnyByUnitOfMeasure() {
        super("Remove_any_by_unit_of_measure", "удалить из коллекции один элемент, значение поля unitOfMeasure которого эквивалентно заданному");
    }
    public void execute(String arg, Root root, Console console, ConsoleParsing parsingInterface) {
        String argument = arg.trim().toUpperCase();
            UnitOfMeasure unitOfMeasure;
            while (true) {
                try {
                    unitOfMeasure = parsingInterface.toRightUnitOfMeasure(argument);
                    break;
                } catch (VariableException | IllegalArgumentException e) {
                    console.print("Введите unitOfMeasure, возможные варианты:PCS, MILLILITERS, GRAMS. Вы ввели: " + argument);
                    argument = console.scanner().trim().toUpperCase();
                }
            }
            root.removeAnyByUnitOfMeasure(unitOfMeasure);

    }
}
