package com.krotos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KartaZakupowTest {

    double delta=0.0001;


    @Test
    void newList(){
        KartaZakupow kartaZakupow=new KartaZakupow();
        assertEquals(0,kartaZakupow.getItemCount());
    }

    @Test
    void getBalance() {
        KartaZakupow kartaZakupow=new KartaZakupow();
        kartaZakupow.addItem("baton",2.2);
        kartaZakupow.addItem("coal",13);
        assertEquals(15.2,kartaZakupow.getBalance(),delta);
    }

    @Test
    void addItem() {
        KartaZakupow kartaZakupow=new KartaZakupow();
        kartaZakupow.addItem("baton",2.2);
        kartaZakupow.addItem("coal",13);
        int beforeCount=kartaZakupow.getItemCount();
        double beforeBalance=kartaZakupow.getBalance();
        kartaZakupow.addItem("cat",13);
        assertEquals(beforeCount+1,kartaZakupow.getItemCount());
        assertEquals(beforeBalance+13,kartaZakupow.getBalance());
    }

    @Test
    void removeItem() {
        KartaZakupow kartaZakupow=new KartaZakupow();
        kartaZakupow.addItem("baton",2.2);
        kartaZakupow.addItem("coal",13);
        int beforeCount=kartaZakupow.getItemCount();
        double beforeBalance=kartaZakupow.getBalance();

        try {
            kartaZakupow.removeItem("coal");
        } catch (KartaZakupow.ProductNotFoundException e) {
            e.printStackTrace();
        }
        assertThrows(KartaZakupow.ProductNotFoundException.class,()->kartaZakupow.removeItem("cos"));
        assertEquals(beforeCount-1,kartaZakupow.getItemCount());
        assertEquals(beforeBalance-13,kartaZakupow.getBalance(),delta);

    }

    @Test
    void getItemCount() {
        KartaZakupow kartaZakupow=new KartaZakupow();
        kartaZakupow.addItem("baton",2.2);
        assertEquals(1,kartaZakupow.getItemCount());
    }

    @Test
    void empty() {
        KartaZakupow kartaZakupow=new KartaZakupow();
        kartaZakupow.addItem("baton",2.2);
        kartaZakupow.addItem("coal",13);
        kartaZakupow.empty();
        assertEquals(0,kartaZakupow.getItemCount());
    }
}