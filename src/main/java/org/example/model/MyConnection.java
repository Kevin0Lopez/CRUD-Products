package org.example.model;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MyConnection {
    private static MyConnection myConnection;
    private BasicDataSource bds;

    private MyConnection() {
    }

    public static MyConnection getInstance() {
        if (myConnection == null) {
            myConnection = new MyConnection();
        }
        return myConnection;
    }

    private BasicDataSource getDataSource() {
        if (bds == null) {
            bds = new BasicDataSource();
            bds.setUrl("jdbc:mysql://localhost/bd_store");
            bds.setUsername("root");
            bds.setPassword("");
            bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
            bds.setInitialSize(5);
            bds.setMinIdle(2);
            bds.setMaxIdle(5);
            bds.setMaxTotal(20);
            bds.setMaxWaitMillis(5000);
        }
        return bds;
    }


    public Connection toConnect() {
        Connection connection = null;
        try {
            connection = this.getDataSource().getConnection();
            System.out.println("Conexion a BD realizada correctamente");
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la BD");
            e.printStackTrace();
        }
        return connection;
    }

    public void toDisconect(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
            System.out.println("Conexion a base de datos cerrada correctamente");
        } catch (SQLException e) {
            System.out.println("No se pudo cerrar la conexion");
            e.printStackTrace();
        }
    }
}