package com.krotos;

public class Produkt {
    private int id;
    private String nazwa;
    private double cena;
    private int quantity;

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }

    public int getQuantity() {
        return quantity;
    }

    public Produkt(int id, String nazwa, double cena, int quantity) {

        this.id = id;
        this.nazwa = nazwa;
        this.cena = cena;
        this.quantity = quantity;
    }
}
