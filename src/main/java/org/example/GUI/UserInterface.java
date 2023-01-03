package org.example.GUI;

import org.example.dao.ProductDAO;
import org.example.model.Product;
import org.example.singleton.MyConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserInterface {

    private Product product;
    private final ProductDAO productDAO = new ProductDAO();
    private HashMap<String, Integer> map;

    private final MyConnection myConnection;
    private Connection connection;

    private JPanel jPanel1;
    private JButton btn_register;
    private JLabel name_label;
    private JLabel price_label;
    private JLabel made_in_label;
    private JLabel creation_date_label;
    private JLabel expiration_date_label;
    private JTextField text_name;
    private JTextField text_price;
    private JTextField text_cration_date;
    private JTextField text_expiration_date;
    private JButton btn_reset;
    private JComboBox comboBox_made_in;
    private JButton btn_showData;
    private JTextArea textArea1;
    private JButton btn_modify;
    private JButton btn_delete;
    private JTextField text_id;
    private JButton btn_search;
    private JLabel id_label;
    private JTable table1;

    public UserInterface() {
        myConnection = MyConnection.getInstance();
        JFrame jFrame = new JFrame();
        jFrame.setBounds(0, 0, 920, 500);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(jPanel1);
        comboBox_made_in.setSelectedIndex(-1);
        btn_register.addActionListener(e -> register());
        btn_reset.addActionListener(e -> reset());
        btn_showData.addActionListener(e -> showData());
        btn_delete.addActionListener(e -> delete());
        btn_modify.addActionListener(e -> modify());
        btn_search.addActionListener(e -> search());
        Object[] columnNames = {"id", "name", "price", "made_in", "creation_date", "expiration_date"};
        table1.setModel(new DefaultTableModel(columnNames, 0));
        jFrame.setVisible(true);

    }

    private void register() {
        if (unavailableToRegister()) {
            JOptionPane.showMessageDialog(null, "Please introduce all the required information");
        } else {
            String name = text_name.getText();
            int price = Integer.parseInt(text_price.getText());
            String made_in = Objects.requireNonNull(comboBox_made_in.getSelectedItem()).toString();
            String creation_date = text_cration_date.getText();
            String expiration_date = text_expiration_date.getText();

            product = new Product(name, price, made_in, creation_date, expiration_date);
            productDAO.create(product);
            reset();
            showData();
        }
    }

    private void showData() {
        ArrayList<Product> listOfProducts = productDAO.read();
        textArea1.setText("");
        listOfProducts.forEach(product -> textArea1.append(product.toString()));

        createTable();
    }

    private void modify() {
        if (unavailableToRegister() || text_id.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Please introduce all the required information");
        } else {
            int id = Integer.parseInt(text_id.getText());
            String name = text_name.getText();
            int price = Integer.parseInt(text_price.getText());
            String made_in = Objects.requireNonNull(comboBox_made_in.getSelectedItem()).toString();
            String creation_date = text_cration_date.getText();
            String expiration_date = text_expiration_date.getText();

            product = new Product(id, name, price, made_in, creation_date, expiration_date);
            productDAO.update(product);
            reset();
            showData();
        }
    }

    private void delete() {
        Matcher matcher = Pattern.compile("^[0-9]+$").matcher(text_id.getText());
        if (text_id.getText().isBlank() || !matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Introduce the ID of the product you want to delete");
        } else {
            int id = Integer.parseInt(text_id.getText());
            product = new Product();
            product.setId(id);
            productDAO.delete(product);
            text_id.setText("");
            showData();
            Icon icon = new ImageIcon();
        }
    }

    private void search() {
        Matcher matcher = Pattern.compile("^[0-9]+$").matcher(text_id.getText());

        if (text_id.getText().isBlank() || !matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Please introduce the id to search the product");
        } else {
            int id = Integer.parseInt(text_id.getText());
            product = productDAO.search(id);

            if (product != null) {

                textArea1.setText("");
                textArea1.append(product.toString());
                text_name.setText(product.getName());
                text_price.setText(String.valueOf(product.getPrice()));
                text_cration_date.setText(product.getCreation_date());
                text_expiration_date.setText(product.getExpiration_date());

                map = new HashMap<>();
                for (int i = 0; i < comboBox_made_in.getItemCount(); i++) {
                    map.put(comboBox_made_in.getItemAt(i).toString(), i);
                }

                comboBox_made_in.setSelectedIndex(map.get(product.getMade_in()));
            } else reset();
        }
    }

    private void reset() {
        text_id.setText("");
        text_name.setText("");
        text_price.setText("");
        text_cration_date.setText("");
        text_expiration_date.setText("");
        textArea1.setText("");
        comboBox_made_in.setSelectedIndex(-1);

        Object[] columnNames = {"id", "name", "price", "made_in", "creation_date", "expiration_date"};
        table1.setModel(new DefaultTableModel(columnNames, 0));
    }

    private boolean unavailableToRegister() {

        return text_name.getText().isBlank()
                || text_price.getText().isBlank()
                || text_cration_date.getText().isBlank()
                || text_expiration_date.getText().isBlank()
                || Objects.requireNonNull(comboBox_made_in.getSelectedItem()).toString().isBlank();
    }

    private void createTable() {
        ArrayList<Product> listOfProducts = productDAO.read();

        Product[] products = listOfProducts.toArray(new Product[0]);

        String[] columnNames = {"id", "name", "price", "made_in", "creation_date", "expiration_date"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        table1.setModel(tableModel);

        for (Product e : products) {
            int id = e.getId();
            String name = e.getName();
            int price = e.getPrice();
            String madeIn = e.getMade_in();
            String creationDate = e.getCreation_date();
            String expirationDate = e.getExpiration_date();

            Object[] data = {id, name, price, madeIn, creationDate, expirationDate};

            tableModel.addRow(data);
        }

    }

}
