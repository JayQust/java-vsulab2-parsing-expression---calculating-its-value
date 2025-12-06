package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Парсер выражений — реализует рекурсивный спуск.
 * Поддерживает: бинарные операции: + - * / ^; функции: sin(x), cos(x), sqrt(x), max(a,b); переменные; скобки;
 */
public class Parser {
    private final  List<Token> tokens;
    private int pos = 0;

    /**
     * @param tokens список токенов, полученных от лексера
     */
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token peek() {
        return tokens.get(pos);
    }

    private Token next() {
        return tokens.get(pos++);
    }

    /**
     * Точка входа - полный разбор выражения.
     */
    public ExpressionNode parse() {
        ExpressionNode expr = parseExpression();
        if (peek().type() != TokenType.END)
            throw new RuntimeException("Лишние символы после выражения: " + peek().text());

        return expr;
    }

    /**
     * parseExpression -> parseTerm ((+|-) parseTerm)
     */
    private ExpressionNode parseExpression() {
        ExpressionNode node = parseTerm();

        while (peek().type() == TokenType.PLUS || peek().type() == TokenType.MINUS) {
            TokenType op = next().type();
            node = new BinaryOpNode(node, op, parseTerm());
        }

        return node;
    }

    /**
     * parseTerm → parsePower ((*|/) parsePower)*
     */
    private ExpressionNode parseTerm() {
        ExpressionNode node = parsePower();

        while (peek().type() == TokenType.MUL || peek().type() == TokenType.DIV) {
            TokenType op = next().type();
            node = new BinaryOpNode(node, op, parsePower());
        }

        return node;
    }

    /**
     * parsePower → parseFactor (^ parseFactor)*
     */
    private ExpressionNode parsePower() {
        ExpressionNode node = parseFactor();

        while (peek().type() == TokenType.POW) {
            next();
            node = new BinaryOpNode(node, TokenType.POW, parseFactor());
        }

        return node;
    }

    /**
     * parseFactor → NUMBER | IDENTIFIER | IDENTIFIER '(' args ')' |'(' expression ')' |'-' factor
     */
    private ExpressionNode parseFactor() {
        Token tok = peek();

        switch (tok.type()) {
            case NUMBER -> {next(); 
                return new NumberNode(Double.parseDouble(tok.text()));
            }
            case IDENTOFIER -> {next(); 
                String name = tok.text();
                if (peek().type() == TokenType.LPAREN) {next(); 
                    List<ExpressionNode> args = new ArrayList<>();
                    if  (peek().type() != TokenType.RPAREN) {
                        do {
                            args.add(parseExpression());
                        } while (peek().type() == TokenType.COMMA && next() != null);
                    }
                    if (peek().type() != TokenType.RPAREN)
                        throw new RuntimeException("Ожидалась ')'");
                    next();

                    return new FunctionNode(name, args);
                }
                return new VariableNode(name);
            }
            case LPAREN -> {next();
                ExpressionNode node = parseExpression();
                if (peek().type() != TokenType.RPAREN)
                    throw new RuntimeException("Ожидалась ')'");
                next();
                return node;
            }
            case MINUS -> {next();
                return new BinaryOpNode(new NumberNode(0), TokenType.MINUS, parseFactor());
            }
        }

        throw new RuntimeException("Неожиданный токен: " + tok.text());
    }
}
