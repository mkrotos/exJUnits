package com.krotos;

import java.sql.*;

public class ProductsStorage implements IProductStorage {
    private Connection myConn = null;
    private Statement myStmt = null;
    private ResultSet myRs = null;

    private String dbUrl = "jdbc:mysql://localhost:3306/productbase";
    private String noSSL = "?serverTimezone=UTC&useSSL=false";
    private String user = "student";
    private String pass = "student";

    public Produkt read(String name) throws SQLException {
        Produkt produkt = null;
        try {
            // 1. Get connection
            myConn = DriverManager.getConnection(dbUrl + noSSL, user, pass);
            // 2. Create a statement
            myStmt = myConn.prepareStatement("select * from products where name=?");
            ((PreparedStatement) myStmt).setString(1, name);
            // 3. Execute SQL query
            myRs = ((PreparedStatement) myStmt).executeQuery();
            // 4. Process the result set
            while (myRs.next()) {
                produkt = new Produkt(
                        myRs.getInt("id"),
                        myRs.getString("name"),
                        myRs.getDouble("price"),
                        myRs.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
        return produkt;

    }
}

