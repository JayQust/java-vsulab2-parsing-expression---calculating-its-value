package com.example;

import java.util.List;

/**
 * Считывает выражение, выполняет разбор и выводит результат.
 */
public class App {

    public static void main(String[] args) {
        try {
            System.out.println("Введите выражение:");
            String input = new java.util.Scanner(System.in).nextLine();

            Lexer lexer = new Lexer(input);
            List<Token> tokens = lexer.tokenize();

            Parser parser = new Parser(tokens);
            ExpressionNode expr = parser.parse();

            Evaluator evaluator = new Evaluator();
            double result = expr.evaluate(evaluator);

            System.out.println("Результат = " + result);
        } catch (RuntimeException e) {System.err.println("Ошибка: " + e.getMessage());}
    }
}


