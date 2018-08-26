package com.krotos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

class KartaZakupowTest {
    ProductsStorage productsStorage = new ProductsStorage();
    KartaZakupow kartaZakupow;
    double delta = 0.0001;

    private IProductStorage createStorageMock() throws SQLException {
        IProductStorage mockStorage = mock(IProductStorage.class);

        Map<String, Produkt> map = new HashMap<>();
        Produkt prod1 = new Produkt(1, "milk", 2.99, 10);
        Produkt prod2 = new Produkt(2, "beer", 3.99, 50);
        map.put(prod1.getName(), prod1);
        map.put(prod2.getName(), prod2);

        doAnswer(invoc -> {
            return map.get(invoc.getArgument(0));
        }).when(mockStorage).read(anyString());

        doAnswer(invoc -> {
            Produkt produkt = invoc.getArgument(0);
            String name = produkt.getName();
            map.put(name, produkt);
            return null;
        }).when(mockStorage).addProduct(any(Produkt.class));

        doAnswer(invoc -> {
            return null;
        }).when(mockStorage).setQuantity(anyString(), anyInt());


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