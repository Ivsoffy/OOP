package ru.nsu.ivchenko;

public class ExpressionUtils {
    /**
     * Функция для рекурсивного перевода строки в выражение.
     *
     * @param expr - выражение в виде строки
     * @return итоговое выражение
     */
    public static Expression buildFromString(String expr) {
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
                    return new Add(buildFromString(expr.substring(0, i)),
                            buildFromString(expr.substring(i + 1)));
                }
                if (c == '-') {
                    return new Sub(buildFromString(expr.substring(0, i)),
                            buildFromString(expr.substring(i + 1)));
                }
                if (c == '*') {
                    return new Mul(buildFromString(expr.substring(0, i)),
                            buildFromString(expr.substring(i + 1)));
                }
                if (c == '/') {
                    return new Div(buildFromString(expr.substring(0, i)),
                            buildFromString(expr.substring(i + 1)));
                }
            }
        }

        throw new IllegalArgumentException("Invalid expression: " + expr);
    }
}
