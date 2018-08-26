package com.krotos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KartaZakupowTest {
    ProductsStorage productsStorage=new ProductsStorage();
    KartaZakupow kartaZakupow;
    double delta=0.0001;


    @BeforeEach
    public void before(){
        kartaZakupow=KartaZakupow.createWith(productsStorage);
    }


    @Test
    void newList(){
        assertEquals(0,kartaZakupow.getItemCount());
    }

    @Test
    void getBalance() {
        kartaZakupow.addItem("milk");
        kartaZakupow.addItem("beer");
        assertEquals(15.2,kartaZakupow.getBalance(),delta);
    }

    @Test
    void addItem() {
        kartaZakupow.addItem("milk");
        kartaZakupow.addItem("beer");
        int beforeCount=kartaZakupow.getItemCount();
        double beforeBalance=kartaZakupow.getBalance();
        kartaZakupow.addItem("cat");
        assertEquals(beforeCount+1,kartaZakupow.getItemCount());
        assertEquals(beforeBalance+13,kartaZakupow.getBalance());
    }

    @Test
    void removeItem() {
        kartaZakupow.addItem("milk");
        kartaZakupow.addItem("beer");
        int beforeCount=kartaZakupow.getItemCount();
        double beforeBalance=kartaZakupow.getBalance();

        try {
            kartaZakupow.removeItem("milk");
        } catch (KartaZakupow.ProductNotFoundException e) {
            e.printStackTrace();
        }
        assertThrows(KartaZakupow.ProductNotFoundException.class,()->kartaZakupow.removeItem("cos"));
        assertEquals(beforeCount-1,kartaZakupow.getItemCount());
        assertEquals(beforeBalance-13,kartaZakupow.getBalance(),delta);

    }

    @Test
    void getItemCount() {
        kartaZakupow.addItem("milk");
        assertEquals(1,kartaZakupow.getItemCount());
    }

    @Test
    void empty() {
        kartaZakupow.addItem("milk");
        kartaZakupow.addItem("beer");
        kartaZakupow.empty();
        assertEquals(0,kartaZakupow.getItemCount());
    }
}