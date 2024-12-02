package ru.nsu.ivchenko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SubTest {

    @Test
    void testPrint() {
        Sub subTest = new Sub(new Number(5), new Number(10));

        assertEquals("(5-10)", subTest.toString());
    }

    @Test
    void testDerivative() {
        Sub subTest = new Sub(new Number(5), new Variable("x"));
        Expression e = subTest.derivative("x");
        assertEquals("(0-1)", e.toString());
    }

    @Test
    void testEvalOne() {
        Sub subTest = new Sub(new Number(5), new Number(10));
        double testEval = subTest.eval("");
        assertEquals(-5, testEval);
    }

    @Test
    void testEvalTwo() {
        Sub subTest = new Sub(new Number(5), new Variable("x"));
        double testEval = subTest.eval("x = 10");
        assertEquals(-5, testEval);
    }
}