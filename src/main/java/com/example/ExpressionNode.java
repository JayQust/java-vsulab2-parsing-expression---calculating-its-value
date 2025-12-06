package com.example;

/**
 * Базовый интерфейс всех узлов AST (дерева разбора).
 */
public interface ExpressionNode {
    /**
     * Вычисляет значение выражений.
     * @param evaluator контекст, одержащий значение переменных и функций
     * @return вычесленный резуль
     */
    double evaluate(Evaluator evaluator);
}
