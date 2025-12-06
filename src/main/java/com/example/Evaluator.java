package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//import java.util.*;

/**
 * Выполняет вычисление AST-дерева.
 * Содержит значения переменных и функции.
 */
public class Evaluator {
    private final Map<String, Double> variables = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Возвращает значение переменной, запрашивая пользователя, если переменная используется впервые.
     * @param name имя переменной
     */
    public double getVariable(String name) {
        if (!variables.containsKey(name)){
            System.out.print("Введите значение переменной " + name + ": ");
            variables.put(name, scanner.nextDouble());
        }
        return variables.get(name);
    }

    /**
     * Вызывает встроенную функцию.
     * @param name имя функции
     * @param args аргументы
     */
    public double callFunction(String name, List<ExpressionNode> args) {
        return switch (name) {
            case "sin" -> Math.sin(args.get(0).evaluate(this));
            case "cos" -> Math.cos(args.get(0).evaluate(this));
            case "sqrt" -> Math.sqrt(args.get(0).evaluate(this));
            case "max" -> Math.max(args.get(0).evaluate(this), args.get(1).evaluate(this));
            default -> throw new RuntimeException("Неизвестная функция: " + name);
        };
    }
}
