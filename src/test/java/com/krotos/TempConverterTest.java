package com.krotos;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TempConverterTest {
    TempConverter conv = new TempConverter();
    double delta = 0.0001;

    @BeforeEach
    public void beforeEach() {
        System.out.println("kolejny test");
    }

    @Test
    void fahrToCels() {
        Assert.assertEquals(20, conv.fahrToCels(68), delta);
    }

    @Test
    void celsToFahr() {
        Assert.assertEquals(32, conv.celsToFahr(0), delta);
    }
}