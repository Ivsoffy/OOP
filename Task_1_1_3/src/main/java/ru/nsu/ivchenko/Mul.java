package ru.nsu.ivchenko;

/**
 * Класс, в котором реализована операция умножения.
 */
public class Mul extends Expression {

    private final Expression first;
    private final Expression second;

    /**
     * Конструктор класса.
     *
     * @param first  - первое выражение.
     * @param second - второе выражение.
     */
    public Mul(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Выводит выражение в читаемом виде.
     */
    @Override
    public String toString() {
        return "(" + first.toString() + "*" + second.toString() + ")";
    }

    /**
     * Производит дифференцирование выражения.
     *
     * @param x переменная, по которой дифференцируем.
     * @return возвращает новое выражение.
     */
    @Override
    public Expression derivative(String x) {
        return new Add(new Mul(first.derivative(x), second), new Mul(first, second.derivative(x)));
    }


    /**
     * Реализует подстановку.
     *
     * @param vars - строка вида "var1 = val1; var2 = val2"
     * @return результат.
     */
    @Override
    public double eval(String vars) {
        return first.eval(vars) * second.eval(vars);
    }
}