package ru.nsu.ivchenko;

/**
 * The class provides an implementation of summation of two expressions.
 */
public class Add implements Expression {

    private final Expression a;
    private final Expression b;

    /**
     * Конструктор класса.
     *
     * @param one первое выражение.
     * @param two второе выражение.
     */
    public Add(Expression one, Expression two) {
        a = one;
        b = two;
    }

    /**
     * Производит вывод выражения в стандартный поток вывода.
     */
    @Override
    public void print() {
        System.out.print("(");
        a.print();
        System.out.print("+");
        b.print();
        System.out.print(")");
    }

    /**
     * Производит дифференцирование выражения.
     *
     * @param x переменная, по которой дифференцируем.
     * @return возвращает новое выражение.
     */
    @Override
    public Expression derivative(String x) {
        return new Add(a.derivative(x), b.derivative(x));
    }

    /**
     * Производит вычисление суммы.
     *
     * @param vars строка для подстановки, передаваемая в виде
     *             "var1 = val1; var2 = val2".
     * @return вычисленное значение.
     */
    @Override
    public double eval(String vars) {
        return a.eval(vars) + b.eval(vars);
    }
}