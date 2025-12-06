package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Лексер - преобразует строку математического выражения в последовательность токенов (лексем ) 
 */
public class Lexer {
    private final String input;
    private int pos = 0;
    
    /**
     * создает лексер для указанной строки
     * 
     * @param input выражение пользователя
     */
    public Lexer(String input) {
        this.input = input.replaceAll("\\s+", "");
    }

    /**
     * Возвращает текущий символ без продвижения позиции.
     */
    private char peek() {
        return pos < input.length() ? input.charAt(pos) : '\0';
    }

    /**
     * Возвращет текущий символ со сдвигом позиции на 1.
     */
    private char next() {
        return pos < input.length() ? input.charAt(pos++) : '\0';
    }

    /**
     * Разбирает строку на токены.
     * 
     * @return список токенов
     */
    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (pos < input.length()) {
            char c = peek();

            if (Character.isDigit(c)) {
                tokens.add(readNumber());
                continue;
            }

            if (Character.isLetter(c)) {
                tokens.add(readIdentifier());
                continue;
            }

            switch (c) {
                case '+': tokens.add(new Token(TokenType.PLUS, "+")); break;
                case '-': tokens.add(new Token(TokenType.MINUS, "-")); break;
                case '*': tokens.add(new Token(TokenType.MUL, "*")); break;
                case '/': tokens.add(new Token(TokenType.DIV , "/")); break;
                case '^': tokens.add(new Token(TokenType.POW , "^")); break;
                case '(': tokens.add(new Token(TokenType.LPAREN , "(")); break;
                case ')': tokens.add(new Token(TokenType.RPAREN , ")")); break;
                case ',': tokens.add(new Token(TokenType.COMMA, ",")); break;
                default:
                    throw new RuntimeException("Недопустимый символ: " + c);
            }

            pos++;
        }

        tokens.add(new Token(TokenType.END, ""));
        return tokens;
    } 

    /**
     * Считывает числовой литерал.
     */
    private Token readNumber() {
        int start = pos;
        while (Character.isDigit(peek()) || peek() == '.') next();
        return new Token(TokenType.NUMBER, input.substring(start, pos));
    }

    /**
     * Считывает имя переменной или функции. 
     */
    private Token readIdentifier() {
        int start = pos;
        while (Character.isLetterOrDigit(peek())) next();
        return new Token(TokenType.IDENTOFIER, input.substring(start, pos));
    }
}
