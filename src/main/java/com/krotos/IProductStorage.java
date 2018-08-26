package com.krotos;

import java.sql.SQLException;

public interface IProductStorage {

    public Produkt read(String name) throws SQLException;
}
