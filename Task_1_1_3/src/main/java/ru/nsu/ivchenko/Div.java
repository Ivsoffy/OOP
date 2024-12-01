package ru.nsu.ivchenko;

/**
 * Класс, в котором реализована операция деления.
 */
public class Div extends Expression {

    private final Expression a;
    private final Expression b;

    /**
     * Конструктор класса.
     *
     * @param top    - первое выражение.
     * @param bottom - второе выражение.
     */
    public Div(Expression top, Expression bottom) {
        a = top;
        b = bottom;
    }

    /**
     * Выводит выражение в читаемом виде.
     */
    @Override
    public String toString() {
        return "(" + a.toString() + "/" + b.toString() + ")";
    }

    /**
     * Производит дифференцирование выражения.
     *
     * @param x переменная, по которой дифференцируем.
     * @return возвращает новое выражение.
     */
    @Override
    public Expression derivative(String x) {
        return new Div(new Sub(new Mul(a.derivative(x), b), new Mul(a, b.derivative(x))),
            new Mul(b, b));
    }


    /**
     * Реализует подстановку.
     *
     * @param vars - строка вида "var1 = val1; var2 = val2"
     * @return результат.
     */
    @Override
    public double eval(String vars) {
        double l = a.eval(vars);
        double r = b.eval(vars);
        if (r == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return l / r;
    }
}