package com.example;

import java.util.List;

/**
 * Узел функции, например sin(x), sqrt(x), max(a,b).
 */
public class FunctionNode implements ExpressionNode {

    private final String name;
    private final List<ExpressionNode> args;

    /**
     * @param name имя функции
     * @param args аргументы функции
     */
    public FunctionNode(String name, List<ExpressionNode> args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public double evaluate(Evaluator evaluator) {
        return evaluator.callFunction(name, args);
    }
}
