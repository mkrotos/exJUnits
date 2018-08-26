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

    @Override
    public void clear() {

    }

    @Override
    public void setQuantity(String name, int quantity) throws SQLException {
        try {
            // 1. Get connection
            myConn = DriverManager.getConnection(dbUrl + noSSL, user, pass);
            // 2. Create a statement
            myStmt = myConn.prepareStatement(
                    "update products " +
                            "SET quantity=?" +
                            " where name=?");
            ((PreparedStatement) myStmt).setInt(1, quantity);
            ((PreparedStatement) myStmt).setString(2, name);
            // 3. Execute SQL update
            int rowsAffected = ((PreparedStatement) myStmt).executeUpdate();

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
    }

    @Override
    public void addProduct(Produkt produkt) throws SQLException {
        try {
            // 1. Get connection
            myConn = DriverManager.getConnection(dbUrl + noSSL, user, pass);
            // 2. Create a statement
            myStmt = myConn.prepareStatement(
                    "insert into products " +
                            "(id,name,price,quantity) " +
                            " values " +
                            "(?,?,?,?)");
            ((PreparedStatement) myStmt).setInt(1, produkt.getId());
            ((PreparedStatement) myStmt).setString(2, produkt.getName());
            ((PreparedStatement) myStmt).setDouble(3, produkt.getPrice());
            ((PreparedStatement) myStmt).setInt(4, produkt.getQuantity());
            // 3. Execute SQL update
            ((PreparedStatement) myStmt).executeUpdate();

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
    }
}

