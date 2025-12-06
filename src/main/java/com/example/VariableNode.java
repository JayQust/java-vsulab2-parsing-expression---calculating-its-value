package com.example;

/**
 * Узел, представляющий переменную.
 * При вычислении значение будет запрошено у пользователя (один раз).
 */
public class VariableNode implements ExpressionNode {
    private final String name; 

    public VariableNode(String name) {
        this.name = name;
    }

    @Override
    public double evaluate(Evaluator evaluator) {
        return evaluator.getVariable(name);
    }
}
