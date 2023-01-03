package org.example.controller;

import org.example.model.Product;
import org.example.model.ProductDAO;
import org.example.view.UserInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerUserInterface implements ActionListener {

    private Product product = new Product();
    private final ProductDAO productDAO = new ProductDAO();
    private final UserInterface view;

    public ControllerUserInterface(UserInterface view) {
        this.view = view;
        this.view.btn_register.addActionListener(this);
        this.view.btn_showData.addActionListener(this);
        this.view.btn_modify.addActionListener(this);
        this.view.btn_delete.addActionListener(this);
        this.view.btn_search.addActionListener(this);
        this.view.btn_reset.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == this.view.btn_register) this.register();

        else if (e.getSource() == this.view.btn_showData) this.showData();

        else if (e.getSource() == this.view.btn_modify) this.modify();

        else if (e.getSource() == this.view.btn_delete) this.delete();

        else if (e.getSource() == this.view.btn_search) this.search();

        else if (e.getSource() == this.view.btn_reset) this.reset();
    }

    private void register() {
        if (unavailableToRegister()) {
            JOptionPane.showMessageDialog(null, "Please introduce all the required information");
        } else {
            String name = this.view.text_name.getText();
            int price = Integer.parseInt(this.view.text_price.getText());
            String made_in = Objects.requireNonNull(this.view.comboBox_made_in.getSelectedItem()).toString();
            String creation_date = this.view.text_cration_date.getText();
            String expiration_date = this.view.text_expiration_date.getText();

            product = new Product(name, price, made_in, creation_date, expiration_date);
            productDAO.create(product);
            reset();
            showData();
        }
    }

    private void showData() {
        ArrayList<Product> listOfProducts = productDAO.read();
        this.view.textArea1.setText("");
        listOfProducts.forEach(product -> this.view.textArea1.append(product.toString()));

        createTable();
    }

    private void modify() {
        if (unavailableToRegister() || this.view.text_id.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Please introduce all the required information");
        } else {
            int id = Integer.parseInt(this.view.text_id.getText());
            String name = this.view.text_name.getText();
            int price = Integer.parseInt(this.view.text_price.getText());
            String made_in = Objects.requireNonNull(this.view.comboBox_made_in.getSelectedItem()).toString();
            String creation_date = this.view.text_cration_date.getText();
            String expiration_date = this.view.text_expiration_date.getText();

            product = new Product(id, name, price, made_in, creation_date, expiration_date);
            productDAO.update(product);
            reset();
            showData();
        }
    }

    private void delete() {
        Matcher matcher = Pattern.compile("^[0-9]+$").matcher(this.view.text_id.getText());
        if (this.view.text_id.getText().isBlank() || !matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Introduce the ID of the product you want to delete");
        } else {
            int id = Integer.parseInt(this.view.text_id.getText());
            product = new Product();
            product.setId(id);
            productDAO.delete(product);
            this.view.text_id.setText("");
            showData();
        }
    }

    private void search() {
        Matcher matcher = Pattern.compile("^[0-9]+$").matcher(this.view.text_id.getText());

        if (this.view.text_id.getText().isBlank() || !matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Please introduce the id to search the product");
        } else {
            int id = Integer.parseInt(this.view.text_id.getText());
            product = productDAO.search(id);

            if (product != null) {

                this.view.textArea1.setText("");
                this.view.textArea1.append(product.toString());
                this.view.text_name.setText(product.getName());
                this.view.text_price.setText(String.valueOf(product.getPrice()));
                this.view.text_cration_date.setText(product.getCreation_date());
                this.view.text_expiration_date.setText(product.getExpiration_date());

                HashMap<String, Integer> map = new HashMap<>();
                for (int i = 0; i < this.view.comboBox_made_in.getItemCount(); i++) {
                    map.put(this.view.comboBox_made_in.getItemAt(i).toString(), i);
                }

                this.view.comboBox_made_in.setSelectedIndex(map.get(product.getMade_in()));
            } else reset();
        }
    }

    private void reset() {
        this.view.text_id.setText("");
        this.view.text_name.setText("");
        this.view.text_price.setText("");
        this.view.text_cration_date.setText("");
        this.view.text_expiration_date.setText("");
        this.view.textArea1.setText("");
        this.view.comboBox_made_in.setSelectedIndex(-1);

        Object[] columnNames = {"id", "name", "price", "made_in", "creation_date", "expiration_date"};
        this.view.table1.setModel(new DefaultTableModel(columnNames, 0));
    }

    private boolean unavailableToRegister() {

        return this.view.text_name.getText().isBlank()
                || this.view.text_price.getText().isBlank()
                || this.view.text_cration_date.getText().isBlank()
                || this.view.text_expiration_date.getText().isBlank()
                || Objects.requireNonNull(this.view.comboBox_made_in.getSelectedItem()).toString().isBlank();
    }

    private void createTable() {
        ArrayList<Product> listOfProducts = productDAO.read();

        Product[] products = listOfProducts.toArray(new Product[0]);

        String[] columnNames = {"id", "name", "price", "made_in", "creation_date", "expiration_date"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        this.view.table1.setModel(tableModel);

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
