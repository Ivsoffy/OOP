package ru.nsu.ivchenko;

/**
 * Interface for equations.
 */
public interface Expression {
    /**
     * Prints equations.
     */
    void print();

    /**
     * Производит дифференцирование выражения.
     *
     * @param x - variable for derivate (dx)
     * @return new expression
     */
    Expression derivative(String x);

    /**
     * Performs a value substitution instead of a variable.
     *
     * @param vars string to substitute, passed as "var1 = val1; var2 = val2".
     * @return result.
     */
    double eval(String vars);

}