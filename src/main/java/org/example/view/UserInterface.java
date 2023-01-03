package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class UserInterface extends JFrame {


    public JPanel jPanel1;
    public JButton btn_register;
    public JLabel name_label;
    public JLabel price_label;
    public JLabel made_in_label;
    public JLabel creation_date_label;
    public JLabel expiration_date_label;
    public JTextField text_name;
    public JTextField text_price;
    public JTextField text_cration_date;
    public JTextField text_expiration_date;
    public JButton btn_reset;
    public JComboBox comboBox_made_in;
    public JButton btn_showData;
    public JTextArea textArea1;
    public JButton btn_modify;
    public JButton btn_delete;
    public JTextField text_id;
    public JButton btn_search;
    public JLabel id_label;
    public JTable table1;

    public UserInterface() {

        this.setBounds(0, 0, 920, 500);
        this.setLocationRelativeTo(null);
        this.setContentPane(jPanel1);
        comboBox_made_in.setSelectedIndex(-1);
        Object[] columnNames = {"id", "name", "price", "made_in", "creation_date", "expiration_date"};
        table1.setModel(new DefaultTableModel(columnNames, 0));
        this.setVisible(true);
    }
}
