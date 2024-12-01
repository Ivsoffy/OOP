package ru.nsu.ivchenko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NumberTest {

    @Test
    void testNonNegativeNumber() {
        Number num = new Number(10);
        assertEquals("10", num.toString());
    }

    @Test
    void testNegativeNumber() {
        Number num = new Number(-10);
        assertEquals("-10", num.toString());
    }

    @Test
    void testDerivative() {
        Number num = new Number(10);
        Expression newNum = num.derivative("x");
        assertEquals("0", newNum.toString());
    }

    @Test
    void testEval() {
        Number num = new Number(10);
        double returned = num.eval("x = 10");
        assertEquals(returned, 10);
    }
}