package ru.nsu.ivchenko;

/**
 * Класс, в котором реализована операция умножения.
 */
public class Mul extends Expression {

    private final Expression a;
    private final Expression b;

    /**
     * Конструктор класса.
     *
     * @param first  - первое выражение.
     * @param second - второе выражение.
     */
    public Mul(Expression first, Expression second) {
        a = first;
        b = second;
    }

    /**
     * Выводит выражение в читаемом виде.
     */
    @Override
    public String toString() {
        return "(" + a.toString() + "*" + b.toString() + ")";
    }

    /**
     * Производит дифференцирование выражения.
     *
     * @param x переменная, по которой дифференцируем.
     * @return возвращает новое выражение.
     */
    @Override
    public Expression derivative(String x) {
        return new Add(new Mul(a.derivative(x), b), new Mul(a, b.derivative(x)));
    }


    /**
     * Реализует подстановку
     *
     * @param vars - строка вида "var1 = val1; var2 = val2"
     * @return результат.
     */
    @Override
    public double eval(String vars) {
        return a.eval(vars) * b.eval(vars);
    }
}