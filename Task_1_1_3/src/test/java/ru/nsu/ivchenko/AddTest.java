package ru.nsu.ivchenko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AddTest {

    @Test
    void testPrint() {
        Add addTest = new Add(new Number(5), new Number(10));

        assertEquals("(5+10)", addTest.toString());
    }

    @Test
    void testDerivative() {
        Add addTest = new Add(new Number(5), new Variable("x"));
        Expression e = addTest.derivative("x");
        assertEquals("(0+1)", e.toString());
    }

    @Test
    void testEvalOne() {
        Add addTest = new Add(new Number(5), new Number(10));
        double testEval = addTest.eval("");
        assertEquals(15, testEval);
    }

    @Test
    void testEvalTwo() {
        Add addTest = new Add(new Number(5), new Variable("x"));
        double testEval = addTest.eval("x = 10");
        assertEquals(15, testEval);
    }
}