package ru.nsu.ivchenko;

/**
 * Интерфейс для выражений.
 */
public abstract class Expression {

    /**
     * Функция для рекурсивного перевода строки в выражение.
     *
     * @param expr - выражение в виде строки
     * @return итоговое выражение
     */
    public static Expression fromString(String expr) {
        if (expr.matches("\\d+")) { // Если это число
            return new Number(Integer.parseInt(expr));
        }
        if (expr.matches("[a-zA-Z]+")) { // Если это переменная
            return new Variable(expr);
        }

        // Если это выражение в скобках
        if (expr.startsWith("(") && expr.endsWith(")")) {
            expr = expr.substring(1, expr.length() - 1); // Убираем внешние скобки
        }

        // Ищем операцию на верхнем уровне
        int level = 0; // Уровень вложенности скобок
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') {
                level++;
            } else if (c == ')') {
                level--;
            } else if (level == 0) { // Вне вложенных скобок
                if (c == '+') {
                    return new Add(fromString(expr.substring(0, i)),
                        fromString(expr.substring(i + 1)));
                }
                if (c == '-') {
                    return new Sub(fromString(expr.substring(0, i)),
                        fromString(expr.substring(i + 1)));
                }
                if (c == '*') {
                    return new Mul(fromString(expr.substring(0, i)),
                        fromString(expr.substring(i + 1)));
                }
                if (c == '/') {
                    return new Div(fromString(expr.substring(0, i)),
                        fromString(expr.substring(i + 1)));
                }
            }
        }

        throw new IllegalArgumentException("Invalid expression: " + expr);
    }

    /**
     * Выводит выражение в читаемом виде.
     */
    public abstract String toString();

    public void print() {
        System.out.println(toString());
    }

    /**
     * Производит дифференцирование выражения.
     *
     * @param x - переменная по которой дифференцируем (dx)
     * @return новое выражение
     */
    abstract Expression derivative(String x);

    /**
     * Реализует подстановку.
     *
     * @param vars - строка вида "var1 = val1; var2 = val2"
     * @return результат.
     */
    abstract double eval(String vars);
}