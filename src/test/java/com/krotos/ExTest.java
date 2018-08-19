package com.krotos;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExTest {

    String s1="wyraz";
    String s2="cos innego";

    @Test
    public void testA(){
        Assert.assertEquals("Rozne wartosci",s1,s2);
    }

    @Test
    public void testB(){
        Assert.assertNotEquals("Takie same wartosci",s1,s2);
    }
    @Test
    public void testC(){
        Assert.assertSame("Różne referencje",s1,s2);
    }
    @Test
    public void testD(){
        Assert.assertTrue("s1 nie jest dłuższy od s2",s1.length()>s2.length());
    }
    @Test
    public void testE(){
        Assert.assertTrue("s2 nie jest dłuższy od s1",s1.length()<s2.length());
    }
}
