package ru.nsu.ivchenko;

/**
 * Класс, в котором реализованы операции с переменными.
 */
public class Variable extends Expression {

    private final String name;

    /**
     * Конструкто для переменной.
     *
     * @param name - название переменной
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * Выводит выражение в читаемом виде.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Производит дифференцирование выражения.
     *
     * @param x переменная, по которой дифференцируем.
     * @return возвращает новое выражение.
     */
    @Override
    public Expression derivative(String x) {
        if (x.equals(name)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    /**
     * Реализует подстановку
     *
     * @param vars - строка вида "var1 = val1; var2 = val2"
     * @return результат.
     */
    @Override
    public double eval(String vars) {
        String newVar = name + " =";
        int ind = vars.indexOf(name);
        if (ind == -1) {
            throw new IllegalArgumentException("Нет переменной " + name);
        }
        ind += name.length() + 3;
        String newNum = "";
        while (ind < vars.length() && vars.charAt(ind) != ';') {
            newNum += vars.charAt(ind);
            ind++;
        }
        return Integer.parseInt(newNum);
    }
}