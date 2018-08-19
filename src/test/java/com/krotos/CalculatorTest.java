package com.krotos;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator();
    static double delta = 0.00001;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll");
        System.out.println("Delta: " + delta);
    }

    @Test
    void add() {
        Assert.assertEquals(5, calculator.add(2, 3), delta);
    }

    @Test
    void subtract() {
        Assert.assertEquals(3, calculator.subtract(9, 6), delta);
    }

    @Test
    void multiply() {
        Assert.assertEquals(9, calculator.multiply(3, 3), delta);
    }

    @Test
    void divide() {
        Assert.assertEquals(2, calculator.divide(4, 2), delta);
    }

    @Test
    void power() {
        Assert.assertEquals(9, calculator.power(3, 2), delta);
    }

    @Test
    void root() {
        Assert.assertEquals(1.73205, calculator.root(3, 2), delta);
    }
}