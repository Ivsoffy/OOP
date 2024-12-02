package ru.nsu.ivchenko;

/**
 * Интерфейс для выражений.
 */
public interface Expression {

    /**
     * Выводит выражение в читаемом виде.
     */
    String toString();

    /**
     * Производит дифференцирование выражения.
     *
     * @param x - переменная по которой дифференцируем (dx)
     * @return новое выражение
     */
    Expression derivative(String x);

    /**
     * Реализует подстановку.
     *
     * @param vars - строка вида "var1 = val1; var2 = val2"
     * @return результат.
     */
    double eval(String vars);
}