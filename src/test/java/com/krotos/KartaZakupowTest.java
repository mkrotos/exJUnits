package com.krotos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

class KartaZakupowTest {
    ProductsStorage productsStorage = new ProductsStorage();
    KartaZakupow kartaZakupow;
    double delta = 0.0001;

    private IProductStorage createStorageMock() {
        IProductStorage mockStorage = mock(IProductStorage.class);

        try {
            doAnswer(invoc -> {
                Produkt produkt = new Produkt(123, invoc.getArgument(0), 2.99, 5);
                return produkt;
            }).when(mockStorage).read(anyString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mockStorage;
    }


    @BeforeEach
    public void before() {
        kartaZakupow = KartaZakupow.createWith(productsStorage);
        //kartaZakupow=KartaZakupow.createWith(createStorageMock());
    }

    @Test
    void newList() {
        assertEquals(0, kartaZakupow.getItemCount());
    }

    @Test
    void getBalance() {
        kartaZakupow.addItem("milk");
        kartaZakupow.addItem("milk");
        assertEquals(5.98, kartaZakupow.getBalance(), delta);
    }

    @Test
    void addItem() {
        kartaZakupow.addItem("milk");
        kartaZakupow.addItem("milk");
        int beforeCount = kartaZakupow.getItemCount();
        double beforeBalance = kartaZakupow.getBalance();
        kartaZakupow.addItem("milk");
        assertEquals(beforeCount + 1, kartaZakupow.getItemCount());
        assertEquals(beforeBalance + 2.99, kartaZakupow.getBalance());
    }

    @Test
    void removeItem() {
        kartaZakupow.addItem("milk");
        kartaZakupow.addItem("milk");
        int beforeCount = kartaZakupow.getItemCount();
        double beforeBalance = kartaZakupow.getBalance();

        try {
            kartaZakupow.removeItem("milk");
        } catch (KartaZakupow.ProductNotFoundException e) {
            e.printStackTrace();
        }
        assertThrows(KartaZakupow.ProductNotFoundException.class, () -> kartaZakupow.removeItem("cos"));
        assertEquals(beforeCount - 1, kartaZakupow.getItemCount());
        assertEquals(beforeBalance - 2.99, kartaZakupow.getBalance(), delta);

    }

    @Test
    void getItemCount() {
        kartaZakupow.addItem("milk");
        assertEquals(1, kartaZakupow.getItemCount());
    }

    @Test
    void empty() {
        kartaZakupow.addItem("milk");
        kartaZakupow.addItem("beer");
        kartaZakupow.empty();
        assertEquals(0, kartaZakupow.getItemCount());
    }
}