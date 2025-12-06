package com.example;

/**
 * Типы токенов, которые могут встречаться в выражении
 */
public enum TokenType {
    NUMBER,           // число
    IDENTOFIER,       // имя переменной или фунуции 
    PLUS, MINUS,      // + -
    MUL, DIV,         // * /
    POW,              // ^
    LPAREN, RPAREN,   // ( )
    COMMA,            // ,
    END               // конец выражения
}