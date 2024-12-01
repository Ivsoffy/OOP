package ru.nsu.ivchenko;

/**
 * Класс, в котором реализованы операции с числами.
 */
public class Number extends Expression {

    private final int num;

    /**
     * Конструктор для числа.
     *
     * @param num - само число
     */
    public Number(int num) {
        this.num = num;
    }

    /**
     * Выводит выражение в читаемом виде.
     */
    @Override
    public String toString() {
        return String.valueOf(num);
    }

    /**
     * Производит дифференцирование выражения.
     *
     * @param x переменная, по которой дифференцируем.
     * @return возвращает новое выражение.
     */
    @Override
    public Expression derivative(String x) {
        return new Number(0);
    }


    /**
     * Реализует подстановку.
     *
     * @param vars - строка вида "var1 = val1; var2 = val2"
     * @return результат.
     */
    @Override
    public double eval(String vars) {
        return num;
    }
}