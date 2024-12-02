package ru.nsu.ivchenko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Ok linter.
 */
public class ExpressionTest {

    @Test
    void testFromString() {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        Expression e2 = ExpressionUtils.buildFromString("(3+(2*x))");
        assertEquals(e.toString(), e2.toString());
    }

    @Test
    void testFromInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> ExpressionUtils.buildFromString("(3+)"));
    }
}