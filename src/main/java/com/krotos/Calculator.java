package com.krotos;


public class Calculator {

    public double add(int a, int b) {
        return a + b;
    }

    public double subtract(int a, int b) {
        return a - b;
    }

    public double multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        return a / b;
    }

    public double power(int a, int b) {
        return Math.pow(a, b);
    }

    public double root(double a, double b) {
        return Math.pow(a, 1 / b);
    }
}
