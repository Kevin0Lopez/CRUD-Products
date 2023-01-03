package org.example.dao;

import org.example.model.Product;
import org.example.singleton.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProductDAO implements DAO<Product> {

    MyConnection myConnection = MyConnection.getInstance();
    Connection connectionMysql;

    @Override
    public void create(Product object) {
        connectionMysql = myConnection.toConnect();
        String sql = "INSERT INTO `bd_store`.`products` (`name`, `price`, `made_in`, `creation_date`," +
                " `expiration_date`) VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement ps = connectionMysql.prepareStatement(sql)) {

            ps.setString(1, object.getName());
            ps.setInt(2, object.getPrice());
            ps.setString(3, object.getMade_in());
            ps.setString(4, object.getCreation_date());
            ps.setString(5, object.getExpiration_date());
            ps.executeUpdate();
        } catch (SQLException exception) {
            myConnection.toDisconect();
            exception.printStackTrace();
        }

        myConnection.toDisconect();
    }

    @Override
    public ArrayList<Product> read() {
        connectionMysql = myConnection.toConnect();
        String sql = "select * from products";

        try (PreparedStatement ps = connectionMysql.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            ArrayList<Product> listProducts = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt("id")
                        , resultSet.getString("name")
                        , resultSet.getInt("price")
                        , resultSet.getString("made_in")
                        , resultSet.getString("creation_date")
                        , resultSet.getString("expiration_date")
                );

                listProducts.add(product);
            }

            myConnection.toDisconect();
            return listProducts;
        } catch (SQLException ex) {
            myConnection.toDisconect();
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Product object) {
        connectionMysql = myConnection.toConnect();

        String sql = "Update products set name=?, price=?, made_in=?, creation_date=?, expiration_date=? where id=?;";
        try (PreparedStatement ps = connectionMysql.prepareStatement(sql)) {

            ps.setString(1, object.getName());
            ps.setInt(2, object.getPrice());
            ps.setString(3, object.getMade_in());
            ps.setString(4, object.getCreation_date());
            ps.setString(5, object.getExpiration_date());
            ps.setInt(6, object.getId());
            ps.executeUpdate();
            myConnection.toDisconect();

        } catch (SQLException exception) {
            myConnection.toDisconect();
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(Product object) {
        connectionMysql = myConnection.toConnect();
        String sql = "Delete from products where id=?";
        try (PreparedStatement ps = connectionMysql.prepareStatement(sql)) {
            ps.setInt(1, object.getId());
            ps.executeUpdate();
            myConnection.toDisconect();
        } catch (SQLException exception) {
            myConnection.toDisconect();
            exception.printStackTrace();
        }
    }

    public Product search(int id) {

        connectionMysql = myConnection.toConnect();
        String sql = "select * from products where id=?";
        Product product = null;
        try (PreparedStatement ps = connectionMysql.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                 product = new Product(resultSet.getInt("id")
                        , resultSet.getString("name")
                        , resultSet.getInt("price")
                        , resultSet.getString("made_in")
                        , resultSet.getString("creation_date")
                        , resultSet.getString("expiration_date")
                );
            }
            myConnection.toDisconect();
        } catch (SQLException ex) {
            myConnection.toDisconect();
            ex.printStackTrace();
        }


        return product;
    }
}
