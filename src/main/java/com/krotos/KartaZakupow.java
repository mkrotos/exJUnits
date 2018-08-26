package com.krotos;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KartaZakupow {
    private List<Produkt> list = new ArrayList<>();
    ProductsStorage productsStorage;

    public static KartaZakupow createWith(ProductsStorage productsStorage){
        return new KartaZakupow(productsStorage);
    }

    public KartaZakupow(ProductsStorage productsStorage) {
        this.productsStorage = productsStorage;
    }

    public double getBalance() {
        double balance = list.stream().mapToDouble(Produkt::getCena).sum();
        return balance;
    }

    public void addItem(String name) {
        try {
            list.add(productsStorage.read(name));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeItem(String name) throws ProductNotFoundException {
        for (Produkt prod : list) {
            if (prod.getNazwa().equals(name)) {
                list.remove(prod);
                return;
            }
        }
        throw new ProductNotFoundException();
    }

    public int getItemCount() {
        return list.size();
    }


    public void empty() {
        list.clear();
    }


    class ProductNotFoundException extends IOException {
        ProductNotFoundException() {
            super();
        }
    }
}
