package com.krotos;

import org.junit.Assert;
import org.junit.Test;

public class HelloWorldTest {

    //Arrange
    HelloWorld hw=new HelloWorld();

    @Test
    public void testHelloWorld(){
        //Act & Assert
        Assert.assertEquals("Nie zwrocono oczekiwanej wartosci","Hello World",hw.helloWorld());
    }
    @Test
    public void testConcat(){
        String expected="HELLOWORLD";
        String s1="Hello";
        String s2="World";
        Assert.assertEquals("Nie dziala", expected, hw.concatAndMapToUpper(s1,s2));
    }
}
