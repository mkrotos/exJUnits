package com.krotos;

import java.sql.SQLException;

public interface IProductStorage {

    Produkt read(String name) throws SQLException;

    void clear();

    void setQuantity(String name, int quantity) throws SQLException;

    void addProduct(Produkt produkt) throws SQLException;
}
