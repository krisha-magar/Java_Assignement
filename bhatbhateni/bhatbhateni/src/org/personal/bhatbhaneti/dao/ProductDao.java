package org.personal.bhatbhaneti.dao;

import org.personal.bhatbhateni.model.Products;
import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    int save(Products product) throws SQLException, ClassNotFoundException;

    int update(Products product, int id) throws SQLException, ClassNotFoundException;

    int remove(int id) throws SQLException, ClassNotFoundException;

    Products findOne(int id) throws SQLException, ClassNotFoundException;

    List<Products> findAll() throws SQLException, ClassNotFoundException;

    List<Products> search(String query) throws SQLException, ClassNotFoundException;
}
