package com.krotos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KartaZakupow {
    private List<Produkt> list = new ArrayList<>();

    public KartaZakupow() {
    }

    public double getBalance() {
        double balance = list.stream().mapToDouble(Produkt::getCena).sum();
        return balance;
    }

    public void addItem(String name, double price) {
        list.add(new Produkt(name, price));
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
