package com.krotos;

public class Produkt {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Produkt(int id, String name, double price, int quantity) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
