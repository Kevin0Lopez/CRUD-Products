package org.example.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static MyConnection myConnection;
    private Connection connection;

    private MyConnection() {
    }

    public static MyConnection getInstance() {
        if (myConnection == null) {
            myConnection = new MyConnection();
        }
        return myConnection;
    }

    public Connection toConnect() {
        String url = "jdbc:mysql://localhost/bd_store";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion a base de datos realizada correctamente");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void toDisconect() {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
            System.out.println("Conexion a base de datos cerrada correctamente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}