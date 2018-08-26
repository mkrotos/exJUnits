package com.krotos;

import java.sql.*;

public class ProductsStorage {
    Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRs = null;

    String dbUrl = "jdbc:mysql://localhost:3306/productbase";
    String noSSL = "?autoReconnect=true&useSSL=false";
    String user = "student";
    String pass = "student";

    public Produkt read(String name) throws SQLException {
        Produkt produkt=null;
        try {
            // 1. Get connection
            myConn = DriverManager.getConnection(dbUrl + noSSL, user, pass);
            // 2. Create a statement
            myStmt = myConn.prepareStatement("select * from products where name=?");
            ((PreparedStatement) myStmt).setString(0,name);
            // 3. Execute SQL query
            myRs = ((PreparedStatement) myStmt).executeQuery();
            // 4. Process the result set

            produkt=new Produkt(myRs.getInt("id"),myRs.getString("name"),myRs.getDouble("price"),myRs.getInt("quantity"));

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
            return produkt;

        }
    }
}

