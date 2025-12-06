package com.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тесты парсера и вычислителя.
 */
public class ParserTest {

    private double eval(String expr) {
        Lexer lexer = new Lexer(expr);
        Parser parser = new Parser(lexer.tokenize());
        ExpressionNode node = parser.parse();
        Evaluator evaluator = new Evaluator();
        return node.evaluate(evaluator);
    }

    @Test
    public void testSimple() {
        assertEquals(7, eval("3+4"), 1e-9);
    }

    @Test
    public void testBrackets() {
        assertEquals(14, eval("2*(3+4)"), 1e-9);
    }

    @Test
    public void testFunctions() {
        assertEquals(1, eval("sin(3.1415926535/2)"), 1e-3);
    }
}