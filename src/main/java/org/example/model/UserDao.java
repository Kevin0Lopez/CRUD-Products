package org.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private final MyConnection myConnection = MyConnection.getInstance();
    Connection connection;

    public UserDao() {
    }

    public User signIn(User user) {
        connection = myConnection.toConnect();
        User userFromBD = null;
        String sql = "select name,password from bd_store.user where name=? and password=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setInt(2, user.getPassword());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString(1);
                int password = rs.getInt(2);
                userFromBD = new User(name, password);
            }
            rs.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        myConnection.toDisconect(connection);
        return userFromBD;
    }

    public void signUp(User user) {
        connection = myConnection.toConnect();
        String sql = "Insert into bd_store.user (name, password) values(?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setInt(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        myConnection.toDisconect(connection);
    }
}
