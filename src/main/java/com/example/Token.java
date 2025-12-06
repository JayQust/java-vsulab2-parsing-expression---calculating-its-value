package com.example;

/**
 * Token - минимальная лексическая единица входного выражения
 * 
 * @param type тип токена
 * @param text исходный текст токена
 */
public record Token(TokenType type, String text) { }
