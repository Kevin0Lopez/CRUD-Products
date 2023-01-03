package org.example.dao;

import java.util.ArrayList;

public interface DAO<T> {

    void create(T object);

    ArrayList<T> read();

    void update(T object);

    void delete(T object);


}
