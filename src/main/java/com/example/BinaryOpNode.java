package com.example;

/**
 * Узел бинарной операции: +, -, *, /, ^.
 */
public class BinaryOpNode implements ExpressionNode{
    private final ExpressionNode left;
    private final ExpressionNode right;
    private final TokenType op;

    /**
     * @param left левый операнд
     * @param op оператор
     * @param right правый операнд
     */
    public BinaryOpNode(ExpressionNode left, TokenType op, ExpressionNode right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public double evaluate(Evaluator evaluator) {
        return switch (op) {
            case PLUS -> left.evaluate(evaluator) + right.evaluate(evaluator);
            case MINUS -> left.evaluate(evaluator) - right.evaluate(evaluator);
            case MUL -> left.evaluate(evaluator) * right.evaluate(evaluator);
            case DIV -> left.evaluate(evaluator) / right.evaluate(evaluator);
            case POW -> Math.pow(left.evaluate(evaluator), right.evaluate(evaluator));
            default -> throw new RuntimeException("Неизвестный оператор: " + op);
        };
    }
}