package com.example;

/**
 * Узел, представляющий чиловой литерал.
 */
public class NumberNode implements ExpressionNode{
    private final double value;

    public NumberNode(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(Evaluator evaluator) {
        return value;
    }
}
