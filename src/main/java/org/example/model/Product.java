package org.example.model;

public class Product {
    int id;
    String name;
    int price;
    String made_in;
    String creation_date;
    String expiration_date;


    public Product(){}

    public Product(int id, String name, int price, String made_in, String creation_date, String expiration_date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.made_in = made_in;
        this.creation_date = creation_date;
        this.expiration_date = expiration_date;
    }

    public Product(String name, int price, String made_in, String creation_date, String expiration_date) {
        this.name = name;
        this.price = price;
        this.made_in = made_in;
        this.creation_date = creation_date;
        this.expiration_date = expiration_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMade_in() {
        return made_in;
    }

    public void setMade_in(String made_in) {
        this.made_in = made_in;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    @Override
    public String toString() {
        return '{' +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", made_in='" + made_in + '\'' +
                ", creation_date='" + creation_date + '\'' +
                ", expiration_date='" + expiration_date + '\'' +
                '}' + "\n";
    }
}
