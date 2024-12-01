package ru.nsu.ivchenko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class VariableTest {

    @Test
    void testPrint() {
        Variable var = new Variable("x");
        assertEquals("x", var.toString());
    }

    @Test
    void testDerivativeByCurVar() {
        Variable var = new Variable("x");
        Expression e = var.derivative("x");

        assertEquals("1", e.toString());
    }

    @Test
    void testDerivativeByAnotherVar() {
        Variable var = new Variable("x");
        Expression e = var.derivative("y");

        assertEquals("0", e.toString());
    }

    @Test
    void testEvalExeption() {
        Variable var = new Variable("x");
        assertThrows(RuntimeException.class, () -> var.eval("u = 10"));

    }

    @Test
    void testEvalOne() {
        Variable var = new Variable("x");
        double value = var.eval("x = 10");
        assertEquals(10, value);
    }

    @Test
    void testEvalTwo() {
        Variable var = new Variable("x");
        double value = var.eval("x = 10; y = 20");
        assertEquals(10, value);
    }

    @Test
    void testEvalThree() {
        Variable var = new Variable("x");
        double value = var.eval("y = 10; x = 20");
        assertEquals(20, value);
    }

    @Test
    void testEvalFour() {
        Variable var = new Variable("y");
        double value = var.eval("x = 10; y = 20; u = 10");
        assertEquals(20, value);
    }
}