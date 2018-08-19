package com.krotos;

import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class Adnotacje {

    static int i=0;

    // Wykonuje sie przed wszystkimi testami
    @BeforeClass
    public static void beforeAll(){
        System.out.println("BeforeAll");
    }

    //Wykonuje się po wszystkich testach
    @AfterClass
    public static void afterAll(){
        System.out.println("AfterAll");
    }

    @Before
    public void beforeEach(){
        System.out.println("BeforeEach");
        System.out.println("Nastepny test ma nr: "+(i++));
    }

    @After
    public void afterEach(){
        System.out.println("AfterEach");
    }

    @Test
    public void test1(){
        System.out.println("In test 1");
    }
    @Test
    public void test2(){
        System.out.println("In test 2");
    }
    @Test
    public void test3(){
        System.out.println("In test 3");
    }
}
