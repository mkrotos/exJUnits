package com.krotos;

public class HelloWorld {

    public String helloWorld(){
        return "Hello World";
    }

    public String concatAndMapToUpper(String s1, String s2){
        String result=s1+s2;
        return result.toUpperCase();
    }
}
