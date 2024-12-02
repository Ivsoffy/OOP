package ru.nsu.ivchenko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MulTest {

    @Test
    void testPrint() {
        Mul mulTest = new Mul(new Number(5), new Number(10));

        assertEquals("(5*10)", mulTest.toString());
    }

    @Test
    void testDerivative() {
        Mul addTest = new Mul(new Number(5), new Variable("x"));
        Expression e = addTest.derivative("x");
        assertEquals("((0*x)+(5*1))", e.toString());
    }

    @Test
    void testEvalOne() {
        Mul addTest = new Mul(new Number(5), new Number(10));
        double testEval = addTest.eval("");
        assertEquals(50, testEval);
    }

    @Test
    void testEvalTwo() {
        Mul addTest = new Mul(new Number(5), new Variable("x"));
        double testEval = addTest.eval("x = 10");
        assertEquals(50, testEval);
    }
}