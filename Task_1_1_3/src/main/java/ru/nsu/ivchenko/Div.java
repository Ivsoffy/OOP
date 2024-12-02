package ru.nsu.ivchenko;

/**
 * Класс, в котором реализована операция деления.
 */
public class Div implements Expression {

    private final Expression first;
    private final Expression second;

    /**
     * Конструктор класса.
     *
     * @param top    - первое выражение.
     * @param bottom - второе выражение.
     */
    public Div(Expression top, Expression bottom) {
        first = top;
        second = bottom;
    }

    /**
     * Выводит выражение в читаемом виде.
     */
    @Override
    public String toString() {
        return "(" + first.toString() + "/" + second.toString() + ")";
    }

    /**
     * Производит дифференцирование выражения.
     *
     * @param x переменная, по которой дифференцируем.
     * @return возвращает новое выражение.
     */
    @Override
    public Expression derivative(String x) {
        return new Div(
            new Sub(new Mul(first.derivative(x), second), new Mul(first, second.derivative(x))),
            new Mul(second, second));
    }


    /**
     * Реализует подстановку.
     *
     * @param vars - строка вида "var1 = val1; var2 = val2"
     * @return результат.
     */
    @Override
    public double eval(String vars) {
        double l = first.eval(vars);
        double r = second.eval(vars);
        if (r == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return l / r;
    }
}
