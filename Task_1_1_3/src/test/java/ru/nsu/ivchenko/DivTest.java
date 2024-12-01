package ru.nsu.ivchenko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DivTest {

    @Test
    void testPrint() {
        Div mulTest = new Div(new Number(5), new Number(10));

        assertEquals("(5/10)", mulTest.toString());
    }

    @Test
    void testDerivative() {
        Div addTest = new Div(new Number(5), new Variable("x"));
        Expression e = addTest.derivative("x");
        assertEquals("(((0*x)-(5*1))/(x*x))", e.toString());
    }

    @Test
    void testEvalOne() {
        Div addTest = new Div(new Number(5), new Number(0));
        assertThrows(ArithmeticException.class, () -> addTest.eval(""));
    }

    @Test
    void testEvalTwo() {
        Div addTest = new Div(new Number(5), new Variable("x"));
        double testEval = addTest.eval("x = 10");
        assertEquals(0.5, testEval);
    }
}